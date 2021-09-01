package com.selim.basicexample.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.selim.basicexample.R

class AddNewUserActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null
    private val etUsername by lazy { findViewById<EditText>(R.id.et_username) }
    private val etPassword by lazy { findViewById<EditText>(R.id.et_password) }
    private val etEmail by lazy { findViewById<EditText>(R.id.et_email) }
    private val buttonRegister by lazy { findViewById<Button>(R.id.btn_register) }
    private val buttonRegisterToLogin by lazy { findViewById<Button>(R.id.btn_register_to_login) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_user)

        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()

        buttonRegister.setOnClickListener {
            if (etUsername.text.isEmpty()) {
                etUsername.error = "Kullanıcı adınızı giriniz"
                etUsername.requestFocus()
                return@setOnClickListener
            }

            if (etPassword.text.isEmpty()) {
                etPassword.error = "Parolanızı giriniz"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            if (etEmail.text.isEmpty()) {
                etEmail.error = "Email adresinizi giriniz"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            auth?.createUserWithEmailAndPassword(
                etEmail.text.toString(),
                etPassword.text.toString()
            )
                ?.addOnSuccessListener {
                    auth?.currentUser?.let { user ->

                        val request = UserProfileChangeRequest.Builder()
                            .setDisplayName(etUsername.text.toString())
                            .build()

                        auth?.currentUser?.updateProfile(request)?.addOnSuccessListener {
                            addNewUser()
                        }
                            ?.addOnFailureListener {
                                Toast.makeText(
                                    this@AddNewUserActivity,
                                    " username güncellenemedi.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                    }
                }

        }

        buttonRegisterToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addNewUser() {
        auth?.currentUser?.uid?.let {
            val user = HashMap<String, String>()
            user["userId"] = it

            firestore?.collection("user")?.add(user)
                ?.addOnCompleteListener { task ->
                    when (task.isSuccessful) {
                        true -> {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        false -> {
                            Toast.makeText(
                                this@AddNewUserActivity,
                                "Kullanıcı eklenemedi.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
        }

    }
}