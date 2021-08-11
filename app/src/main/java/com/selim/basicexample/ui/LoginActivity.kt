package com.selim.basicexample.ui

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.selim.basicexample.R
import kotlinx.android.synthetic.main.activity_add_new_user.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null

    private val emailAddressEdittextView by lazy { findViewById<EditText>(R.id.login_et_mail) }
    private val passwordEdittextView by lazy { findViewById<EditText>(R.id.login_et_password) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        btn_login.setOnClickListener {
            if(login_et_mail.text.isEmpty())
            {
                login_et_mail.error = "Email adresinizi giriniz"
                login_et_mail.requestFocus()
                return@setOnClickListener
            }
            if(login_et_password.text.isEmpty())
            {
                login_et_password.error = "Parolanızı giriniz"
                login_et_password.requestFocus()
                return@setOnClickListener
            }

            auth?.signInWithEmailAndPassword(
                emailAddressEdittextView.text.toString(),
                passwordEdittextView.text.toString()
            )
                ?.addOnSuccessListener {
                    val user = auth?.currentUser
                    Toast.makeText(this, "Hoşgeldiniz ${user?.displayName}", Toast.LENGTH_LONG)
                        .show()
                }?.addOnFailureListener {
                    Toast.makeText(this, "Kullanıcı Bulunamadı", Toast.LENGTH_LONG)
                        .show()
                }?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
        }

        btn_login_to_register.setOnClickListener {
            val intent = Intent(this, AddNewUserActivity::class.java)
            startActivity(intent)
        }
    }
}