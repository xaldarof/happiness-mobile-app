package pdf.reader.happiness.data.cloud.auth

import com.google.firebase.database.*
import pdf.reader.happiness.data.cache.dao.UserDao
import pdf.reader.happiness.data.cloud.models.UserCloudModel

/**
 * @Author: Temur
 * @Date: 27/08/2022
 */

interface AuthRepository {

    suspend fun login(
        login: String, password: String, onSuccess: () -> Unit,
        onFail: () -> Unit,
        onNotExists: () -> Unit
    )

    suspend fun register(
        login: String, password: String, onSuccess: () -> Unit,
        onFail: () -> Unit,
        onNotExists: () -> Unit
    )

    class Base(private val userDao: UserDao) : AuthRepository {
        override suspend fun login(
            login: String,
            password: String,
            onSuccess: () -> Unit,
            onFail: () -> Unit,
            onNotExists: () -> Unit
        ) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")

            try {
                if (login.isNotEmpty() && password.isNotEmpty()) {
                    databaseReference
                        .addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.hasChild(login)) {
                                    databaseReference.child(login)
                                        .addValueEventListener(object : ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                val model =
                                                    snapshot.getValue(UserCloudModel::class.java)
                                                if (model?.password == password) {
                                                    model.mapToCache()
                                                        .let {
                                                            userDao.insert(it)
                                                            onSuccess()
                                                        }
                                                } else {
                                                    onFail()
                                                }
                                            }

                                            override fun onCancelled(error: DatabaseError) {
                                                onFail()
                                            }

                                        })
                                } else {
                                    onNotExists()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                onFail()
                                error.toException().printStackTrace()
                            }
                        })
                } else {
                    onFail()
                }
            } catch (e: Exception) {
                onFail()
            }
        }

        override suspend fun register(
            login: String,
            password: String,
            onSuccess: () -> Unit,
            onFail: () -> Unit,
            onNotExists: () -> Unit
        ) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")
            try {
                if (login.isNotEmpty() && password.isNotEmpty()) {
                    databaseReference
                        .addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (!snapshot.hasChild(login)) {
                                    val registerModel = UserCloudModel(
                                        login,
                                        password,
                                        balance = 0
                                    )
                                    databaseReference.child(login).setValue(
                                        registerModel
                                    ).addOnSuccessListener {
                                        registerModel.mapToCache()
                                            .let {
                                                userDao.insert(it)
                                                onSuccess()
                                            }
                                    }.addOnFailureListener {
                                        onFail()
                                    }
                                } else {
                                    onFail()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                onFail()
                                error.toException().printStackTrace()
                            }
                        })
                } else {
                    onFail()
                }
            } catch (e: Exception) {
                onFail()
            }
        }
    }
}