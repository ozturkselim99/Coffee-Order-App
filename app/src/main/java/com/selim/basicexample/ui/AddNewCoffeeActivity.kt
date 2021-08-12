package com.selim.basicexample.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.selim.basicexample.R
import com.selim.basicexample.data.MockData
import com.selim.basicexample.model.Coffee
import com.selim.basicexample.model.CoffeeCategory
import kotlinx.android.synthetic.main.activity_add_new_address.*
import kotlinx.android.synthetic.main.activity_add_new_coffee.*
import kotlinx.android.synthetic.main.coffee_item_row.*

class AddNewCoffeeActivity : AppCompatActivity() {

    private var firestore: FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_coffee)

        if(intent.hasExtra("coffee"))
        {
            val updateUser = intent.getSerializableExtra("coffee") as Coffee
            editText_kahveAdi.setText(updateUser.name)
            editText_kahveFiyati.setText(updateUser.price)
            editText_kahveImageUrl.setText(updateUser.imageUrl)
            btn_kahveEkle.setText("Güncelle")
        }

        firestore = FirebaseFirestore.getInstance()

        /*val adapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            MockData.getCoffeeCategories()
        )
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //seçilen kahve kategorisinin idsini alma
                var selectedCategory = spinner.selectedItem as CoffeeCategory
                selectedCategoryId = selectedCategory.id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }*/

        btn_kahveEkle.setOnClickListener {
            if (editText_kahveAdi.text.isEmpty()) {
                editText_kahveAdi.error = "Kahve adı boş geçilemez"
                editText_kahveAdi.requestFocus()
                return@setOnClickListener
            }

            if (editText_kahveFiyati.text.isEmpty()) {
                editText_kahveFiyati.error = "Kahve fiyatı boş geçilemez"
                editText_kahveFiyati.requestFocus()
                return@setOnClickListener
            }
            if(editText_kahveImageUrl.text.isEmpty())
            {
                editText_kahveImageUrl.error = "Resim ekleyiniz"
                editText_kahveImageUrl.requestFocus()
                return@setOnClickListener
            }
            addNewCoffee()

        }
    }

    private fun addNewCoffee() {
        val categoryId = intent.getStringExtra("CATEGORY_ID") ?: ""

        val coffee = Coffee().apply {
            name = editText_kahveAdi.text.toString()
            price = editText_kahveFiyati.text.toString()
            imageUrl = editText_kahveImageUrl.text.toString()
        }

        firestore?.collection("category")?.document(categoryId)?.collection("coffees")?.add(coffee)?.addOnCompleteListener { task ->

            when (task.isSuccessful) {
                true -> {
                    Toast.makeText(
                        this,
                        "${coffee.name} kahvesi Eklendi..",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                }
                false -> Toast.makeText(
                    this,
                    "${coffee.name} kahvesi Eklenemedi..",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }
}