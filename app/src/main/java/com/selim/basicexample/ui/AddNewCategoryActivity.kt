package com.selim.basicexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.selim.basicexample.R
import kotlinx.android.synthetic.main.activity_add_new_category.*

class AddNewCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_category)

        btn_add_category_to_db.setOnClickListener {
            if(et_category_name.text.isEmpty())
            {
                et_category_name.error = "Kategori adını giriniz"
                et_category_name.requestFocus()
                return@setOnClickListener
            }
            if(et_image_url.text.isEmpty())
            {
                et_image_url.error = "Fotoğraf URLsi boş olamaz"
                et_image_url.requestFocus()
                return@setOnClickListener
            }

        }




    }
}