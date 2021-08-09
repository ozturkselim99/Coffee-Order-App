package com.selim.basicexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.selim.basicexample.R
import kotlinx.android.synthetic.main.activity_add_new_user.*

class AddNewUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_user)

        btn_register.setOnClickListener {
            if(et_username.text.isEmpty())
            {
                et_username.error = "Kullanıcı adınızı giriniz"
                et_username.requestFocus()
                return@setOnClickListener
            }
            if(et_password.text.isEmpty())
            {
                et_password.error = "Parolanızı giriniz"
                et_password.requestFocus()
                return@setOnClickListener
            }
            if(et_email.text.isEmpty())
            {
                et_email.error = "Email adresinizi giriniz"
                et_email.requestFocus()
                return@setOnClickListener
            }
            if(et_phone.text.isEmpty())
            {
                et_phone.error = "Telefon numaranızı giriniz"
                et_phone.requestFocus()
                return@setOnClickListener
            }
        }
    }
}