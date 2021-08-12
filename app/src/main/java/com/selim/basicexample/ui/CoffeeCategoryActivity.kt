package com.selim.basicexample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.selim.basicexample.R
import com.selim.basicexample.adapter.CategoryMenuAdapter
import com.selim.basicexample.model.CoffeeCategory
import kotlinx.android.synthetic.main.activity_coffee_category.*

class CoffeeCategoryActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee_category)

        firestore = FirebaseFirestore.getInstance()

        val linearLayout = LinearLayoutManager(this)
        recycler_view_category_menu.layoutManager = linearLayout

        fab_add_category.setOnClickListener {
            val intent = Intent(this, AddNewCategoryActivity::class.java)
            startActivity(intent)
        }

        getCategories()
    }

    private fun getCategories() {
        firestore?.collection("category")?.get()?.addOnSuccessListener { snapshot ->
            val list = arrayListOf<CoffeeCategory>()

            snapshot.documents.forEach { documentSnapshot ->
                documentSnapshot.toObject(CoffeeCategory::class.java)?.let { category ->
                    category.id = documentSnapshot.id
                    list.add(category)
                }
            }

            loadCategories(list)

        }
    }

    private fun loadCategories(list: ArrayList<CoffeeCategory>) {
        val categoryMenuAdapter = CategoryMenuAdapter(list)
        recycler_view_category_menu.adapter = categoryMenuAdapter
    }
}