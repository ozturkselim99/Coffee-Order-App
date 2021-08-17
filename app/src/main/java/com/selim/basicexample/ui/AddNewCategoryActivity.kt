package com.selim.basicexample.ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.selim.basicexample.R
import com.selim.basicexample.model.CoffeeCategory
import kotlinx.android.synthetic.main.activity_add_new_category.*

class AddNewCategoryActivity : AppCompatActivity() {
    private val buttonAdd by lazy { findViewById<View>(R.id.btn_add_category_to_db) }
    private val editTextCategoryName by lazy { findViewById<EditText>(R.id.et_category_name) }
    private val editTextUrl by lazy { findViewById<EditText>(R.id.et_image_url) }

    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_category)

        firestore = FirebaseFirestore.getInstance()

        buttonAdd.setOnClickListener {

            if(editTextCategoryName.text.isEmpty())
            {
                editTextCategoryName.error = "Kategori adını giriniz"
                editTextCategoryName.requestFocus()
                return@setOnClickListener
            }
            if(et_image_url.text.isEmpty())
            {
                et_image_url.error = "Fotoğraf URLsi boş olamaz"
                et_image_url.requestFocus()
                return@setOnClickListener
            }
            addNewCategory()
        }
    }

    private fun addNewCategory() {
        val document = firestore?.collection("category")

        val category = CoffeeCategory().apply {
            name = editTextCategoryName.text.toString()
            imageUrl = editTextUrl.text.toString()
        }

        document?.add(category)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> {
                    Toast.makeText(
                        this,
                        "${category.name} kategorisi Eklendi..",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                }
                false -> Toast.makeText(
                    this,
                    "${category.name} kategorisi Eklenemedi..",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}