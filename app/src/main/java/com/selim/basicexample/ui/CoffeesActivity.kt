package com.selim.basicexample.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.selim.basicexample.AddNewAddressActivity
import com.selim.basicexample.R
import com.selim.basicexample.adapter.CoffeeAdapter
import com.selim.basicexample.adapter.CoffeeHomeAdapter
import com.selim.basicexample.data.MockData
import kotlinx.android.synthetic.main.activity_coffees.*
import kotlinx.android.synthetic.main.activity_main.*

class CoffeesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffees)


        val layoutManager = LinearLayoutManager(this)
        recycler_view_coffees.layoutManager = layoutManager

        val coffeeAdapter = CoffeeAdapter(MockData.getCoffeeList())
        recycler_view_coffees.adapter = coffeeAdapter

        fab_add_coffee.setOnClickListener {
            val intent = Intent(this, AddNewCoffeeActivity::class.java)
            startActivity(intent)
        }
    }
}