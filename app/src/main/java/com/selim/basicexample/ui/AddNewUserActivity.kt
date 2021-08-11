package com.selim.basicexample.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.selim.basicexample.R
import kotlinx.android.synthetic.main.activity_add_new_user.*

class AddNewUserActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_user)

        auth = Firebase.auth

        btn_register.setOnClickListener {
            if (et_username.text.isEmpty()) {
                et_username.error = "Kullanıcı adınızı giriniz"
                et_username.requestFocus()
                return@setOnClickListener
            }

            if (et_password.text.isEmpty()) {
                et_password.error = "Parolanızı giriniz"
                et_password.requestFocus()
                return@setOnClickListener
            }

            if (et_email.text.isEmpty()) {
                et_email.error = "Email adresinizi giriniz"
                et_email.requestFocus()
                return@setOnClickListener
            }

            if (et_phone.text.isEmpty()) {
                et_phone.error = "Telefon numaranızı giriniz"
                et_phone.requestFocus()
                return@setOnClickListener
            }

            auth?.createUserWithEmailAndPassword(
                et_email.text.toString(),
                et_password.text.toString()
            )
                ?.addOnSuccessListener {
                    auth?.currentUser?.let { user ->

                        val request = UserProfileChangeRequest.Builder()
                            .setDisplayName(et_username.text.toString())
                            .build()

                        auth?.currentUser?.updateProfile(request)?.addOnSuccessListener {

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

        btn_register_to_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}