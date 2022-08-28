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

    suspend fun checkUserState(
        loggedOut: () -> Unit
    )

    suspend fun sendTo(
        username: String, count: Int, onSuccess: () -> Unit,
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
                            if (snapshot.getValue(UserCloudModel::class.java) != null) {
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

        override suspend fun checkUserState(
            loggedOut: () -> Unit
        ) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")

            try {
                databaseReference.child(userDao.fetUser()?.login ?: "")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val currentUser = snapshot.getValue(UserCloudModel::class.java)
                            if (currentUser == null) {
                                loggedOut()
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                        }
                    })
            } catch (e: Exception) {

            }
        }

        override suspend fun sendTo(
            username: String,
            count: Int,
            onSuccess: () -> Unit,
            onFail: (String) -> Unit
        ) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")

            try {
                if (username == userDao.fetUser()?.login ?: "") {
                    onFail("Xaxaxaxa ну вы юморист !")
                } else {
                    if (username.isNotEmpty() && count != -1) {
                        if ((userDao.fetUser()?.balance ?: 0) >= count) {
                            databaseReference.child(username)
                                .addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        val model = snapshot.getValue(UserCloudModel::class.java)
                                        if (model != null) {
                                            val current = model.balance
                                            val updated = current + count

                                            databaseReference.child(username).child("balance")
                                                .setValue(updated).addOnSuccessListener {
                                                    invokePayment(count, onSuccess, onFail)
                                                }.addOnFailureListener {
                                                    onFail("Что то пошло не так")
                                                }
                                        } else {
                                            onFail("Пользователь не найден !")
                                        }
                                    }

                                    override fun onCancelled(error: DatabaseError) {
                                        onFail("Что то пошло не так")
                                    }

                                })
                        } else {
                            onFail("Недостаточно монет !")
                        }

                    } else {
                        onFail("Заполните все поля !")
                    }
                }
            } catch (e: Exception) {
                onFail("Что то пошло не так")
            }
        }

    }
}