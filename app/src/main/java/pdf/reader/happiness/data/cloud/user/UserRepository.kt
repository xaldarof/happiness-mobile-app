package pdf.reader.happiness.data.cloud.user

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cache.dao.UserDao
import pdf.reader.happiness.data.cache.models.UserModelDb
import pdf.reader.happiness.data.cloud.models.UserCloudModel

/**
 * @Author: Temur
 * @Date: 27/08/2022
 */

interface UserRepository {

    fun fetchUser(): Flow<UserModelDb?>
    fun fetchUserBalance(): Int
    fun fetchUserBalanceAsFlow(): Flow<Int>
    fun invokePayment(
        count: Int,
        onSuccess: () -> Unit,
        onFail: (String) -> Unit
    )

    fun updateBalance(
        count: Int,
        onSuccess: () -> Unit,
        onFail: (String) -> Unit
    )

    suspend fun syncUser(
        onSuccess: () -> Unit,
        onFail: (String) -> Unit
    )

    class Base(private val userDao: UserDao) : UserRepository {
        override fun fetchUser(): Flow<UserModelDb?> {
            return userDao.fetUserAsFlow()
        }

        override fun fetchUserBalance(): Int {
            return userDao.fetUser()?.balance ?: 0
        }

        override fun fetchUserBalanceAsFlow(): Flow<Int> {
            return userDao.fetUserBalanceAsFlow()
        }

        override fun invokePayment(
            count: Int, onSuccess: () -> Unit,
            onFail: (String) -> Unit,
        ) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")

            try {
                databaseReference.child(userDao.fetUser()?.login ?: "")
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val model = snapshot.getValue(UserCloudModel::class.java)
                            val current = model?.balance ?: 0
                            val updated = current - count

                            databaseReference.child(userDao.fetUser()?.login ?: "")
                                .child("balance").setValue(updated).addOnSuccessListener {
                                    onSuccess()
                                }.addOnFailureListener {
                                    onFail("Что то пошло не так !")
                                }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            onFail("Что то пошло не так !")
                        }

                    })

            } catch (e: Exception) {
                onFail("Что то пошло не так !")
            }
        }

        override fun updateBalance(count: Int, onSuccess: () -> Unit, onFail: (String) -> Unit) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")

            try {
                databaseReference.child(userDao.fetUser()?.login ?: "")
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val model = snapshot.getValue(UserCloudModel::class.java)
                            val current = model?.balance ?: 0
                            val updated = current + count

                            databaseReference.child(userDao.fetUser()?.login ?: "")
                                .child("balance").setValue(updated).addOnSuccessListener {
                                    onSuccess()
                                }.addOnFailureListener {
                                    onFail("Что то пошло не так !")
                                }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            onFail("Что то пошло не так !")
                        }

                    })

            } catch (e: Exception) {
                onFail("Что то пошло не так !")
            }
        }

        override suspend fun syncUser(onSuccess: () -> Unit, onFail: (String) -> Unit) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")

            try {
                databaseReference.child(userDao.fetUser()?.login ?: "")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if(snapshot.getValue(UserCloudModel::class.java) != null) {
                                userDao.insert(
                                    snapshot.getValue(UserCloudModel::class.java)!!.mapToCache()
                                )
                            } else {
                                onFail("Что то пошло не так")
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            onFail("Что то пошло не так")
                        }

                    })
            } catch (e: Exception) {
                onFail("Что то пошло не так")
            }
        }
    }
}