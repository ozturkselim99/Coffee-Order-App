package com.selim.basicexample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.selim.basicexample.R
import com.selim.basicexample.adapter.CoffeeAdapter
import com.selim.basicexample.model.Coffee
import kotlinx.android.synthetic.main.activity_coffees.*

class CoffeesActivity : AppCompatActivity() {

    var firebase: FirebaseFirestore? = null
    private var categoryId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffees)
        categoryId = intent.getStringExtra("CATEGORY_ID") ?: ""

        firebase = FirebaseFirestore.getInstance()

        val layoutManager = LinearLayoutManager(this)
        recycler_view_coffees.layoutManager = layoutManager

        val categoryId = intent.getStringExtra("CATEGORY_ID") ?: ""

        fab_add_coffee.setOnClickListener {
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
        recycler_view_coffees.adapter = coffeeAdapter
    }


}