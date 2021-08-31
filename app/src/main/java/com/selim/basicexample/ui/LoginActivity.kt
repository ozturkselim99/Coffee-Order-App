package com.selim.basicexample.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.selim.basicexample.R

class LoginActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null

    private val emailAddressEdittextView by lazy { findViewById<EditText>(R.id.login_et_mail) }
    private val passwordEdittextView by lazy { findViewById<EditText>(R.id.login_et_password) }
    private val buttonLogin by lazy { findViewById<Button>(R.id.btn_login) }
    private val buttonLoginToRegister by lazy { findViewById<Button>(R.id.btn_login_to_register) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        buttonLogin.setOnClickListener {
            if (emailAddressEdittextView.text.isEmpty()) {
                emailAddressEdittextView.error = "Email adresinizi giriniz"
                emailAddressEdittextView.requestFocus()
                return@setOnClickListener
            }
            if (passwordEdittextView.text.isEmpty()) {
                passwordEdittextView.error = "Parolanızı giriniz"
                passwordEdittextView.requestFocus()
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
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
        }

        buttonLoginToRegister.setOnClickListener {
            val intent = Intent(this, AddNewUserActivity::class.java)
            startActivity(intent)
        }
    }
}