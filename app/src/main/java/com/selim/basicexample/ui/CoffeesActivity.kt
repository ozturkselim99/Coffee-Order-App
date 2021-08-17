package com.selim.basicexample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.selim.basicexample.R
import com.selim.basicexample.adapter.CoffeeAdapter
import com.selim.basicexample.model.Coffee

class CoffeesActivity : AppCompatActivity() {

    var firebase: FirebaseFirestore? = null
    private var categoryId: String = ""
    private val recyclerViewCoffees by lazy { findViewById<RecyclerView>(R.id.recycler_view_coffees) }
    private val fabAddCoffeeButton by lazy { findViewById<RecyclerView>(R.id.fab_add_coffee) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffees)
        categoryId = intent.getStringExtra("CATEGORY_ID") ?: ""

        firebase = FirebaseFirestore.getInstance()

        val layoutManager = LinearLayoutManager(this)
        recyclerViewCoffees.layoutManager = layoutManager

        val categoryId = intent.getStringExtra("CATEGORY_ID") ?: ""

        fabAddCoffeeButton.setOnClickListener {
            val intent = Intent(this, AddNewCoffeeActivity::class.java)
            intent.putExtra("CATEGORY_ID", categoryId)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        getCoffees()
    }


    private fun getCoffees() {
        firebase?.collection("category")?.document(categoryId)?.collection("coffees")?.addSnapshotListener { snapshot, error ->
            val list = ArrayList<Coffee>()
            snapshot?.documents?.forEach { documentSnapshot ->
                documentSnapshot.toObject(Coffee::class.java)?.let { coffee ->
                    coffee.id = documentSnapshot.id
                    list.add(coffee)
                }
            }
            loadCoffees(list)
        }
    }

    private fun loadCoffees(list: ArrayList<Coffee>) {
        val coffeeAdapter = CoffeeAdapter(categoryId, list)
        recyclerViewCoffees.adapter = coffeeAdapter
    }


}