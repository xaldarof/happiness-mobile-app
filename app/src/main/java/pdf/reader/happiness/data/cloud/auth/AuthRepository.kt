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
        onFail: (String) -> Unit,
    )

    suspend fun register(
        login: String, password: String, onSuccess: () -> Unit,
        onFail: (String) -> Unit,
    )

    class Base(private val userDao: UserDao) : AuthRepository {
        override suspend fun login(
            login: String,
            password: String,
            onSuccess: () -> Unit,
            onFail: (String) -> Unit,
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
                                                    onFail("Неверный пароль !")
                                                }
                                            }

                                            override fun onCancelled(error: DatabaseError) {
                                                onFail("Что то пошло не так...")
                                            }

                                        })
                                } else {
                                    onFail("Данный пользователь не существует")
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                onFail("Что то пошло не так...")
                                error.toException().printStackTrace()
                            }
                        })
                } else {
                    onFail("Заполните все поля !")
                }
            } catch (e: Exception) {
                onFail("Что то пошло не так...")
            }
        }

        override suspend fun register(
            login: String,
            password: String,
            onSuccess: () -> Unit,
            onFail: (String) -> Unit,
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
                                        onFail("Что то пошло не так...")
                                    }
                                } else {
                                    onFail("Пользователь уже зарегистрирован !")
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                onFail("Что то пошло не так...")
                                error.toException().printStackTrace()
                            }
                        })
                } else {
                    onFail("Заполните все поля !")
                }
            } catch (e: Exception) {
                onFail("Что то пошло не так...")
            }
        }
    }
}