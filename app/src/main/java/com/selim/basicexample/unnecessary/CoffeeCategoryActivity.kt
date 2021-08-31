/*package com.selim.basicexample.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.selim.basicexample.R
import com.selim.basicexample.adapter.CategoryMenuAdapter
import com.selim.basicexample.model.CoffeeCategory

class CoffeeCategoryActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    private val recyclerViewCategoryMenu by lazy { findViewById<RecyclerView>(R.id.recycler_view_category_menu) }
    private val fabAddCategoryButton by lazy { findViewById<Button>(R.id.fab_add_category) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee_category)

        firestore = FirebaseFirestore.getInstance()

        val linearLayout = LinearLayoutManager(this)
        recyclerViewCategoryMenu.layoutManager = linearLayout

        fabAddCategoryButton.setOnClickListener {
            val intent = Intent(this, AddNewCategoryActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
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
        val categoryAdapter = CategoryMenuAdapter(this, list)
        recyclerViewCategoryMenu.adapter = categoryAdapter
    }
}

 */