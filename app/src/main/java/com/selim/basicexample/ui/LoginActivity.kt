package com.selim.basicexample.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.selim.basicexample.R
import com.selim.basicexample.data.MockData
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btn_login.setOnClickListener {

            var user=MockData.getUserList().find {it.username==login_et_username.text.toString() && it.password==login_et_password.text.toString()}

            if (user != null) {
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"Hoşgeldiniz ${user.username}",Toast.LENGTH_LONG).show()
                finish()
            }
            else
            {
                Toast.makeText(this,"Kullanıcı adı veya şifre yanlış",Toast.LENGTH_LONG).show()
            }

        }
    }
}