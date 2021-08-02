package com.selim.basicexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.coffee_row.*

data class Coffee(var name:String, var image:Int, var fee:String)

class MainActivity : AppCompatActivity() {

    var coffeeList= ArrayList<Coffee>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataSource()

        var adapter = Adapter(coffeeList)
        recyclerView.adapter = adapter

        var linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager


    }

    fun DataSource() : ArrayList<Coffee>
    {
        var coffeeNames = resources.getStringArray(R.array.CoffeeName)
        var coffeeImages = arrayOf(R.drawable.latte, R.drawable.macchiato, R.drawable.cappucino, R.drawable.turkish_coffee)
        var coffeeFees = resources.getStringArray(R.array.CoffeeFee)

        for(i in 0..coffeeImages.size-1)
        {
            var addedCoffee = Coffee(coffeeNames[i], coffeeImages[i], coffeeFees[i])
            coffeeList.add(addedCoffee)
        }
        return coffeeList
    }

}


