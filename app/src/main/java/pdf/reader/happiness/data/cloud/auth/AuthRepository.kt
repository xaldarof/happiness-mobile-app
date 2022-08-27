package pdf.reader.happiness.data.cloud.auth

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

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

    class Base(private val databaseReference: DatabaseReference) : AuthRepository {
        override suspend fun login(
            login: String,
            password: String,
            onSuccess: () -> Unit,
            onFail: () -> Unit,
            onNotExists: () -> Unit
        ) {
            databaseReference.child("users")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.hasChild(login)) {
                            onSuccess()
                        } else {
                            onNotExists()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        onFail()
                    }
                })
        }
    }
}