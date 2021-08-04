package com.selim.basicexample.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.selim.basicexample.Coffee
import com.selim.basicexample.R
import com.selim.basicexample.adapter.Adapter
import com.selim.basicexample.adapter.CartAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.coffee_row.view.*


class MainActivity : AppCompatActivity() {

    var coffeeList=ArrayList<Coffee>()
    var orderedCoffeeList = ArrayList<Coffee>()
    var totalCartPrice:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataSource()

        var adapter = Adapter(coffeeList)
        recyclerView.adapter = adapter

        var linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        adapter.total.observe(this, { tv_total_price.text = it.toString()
            totalCartPrice=it})

        adapter.cart.observe(this, { orderedCoffeeList=it })

        btn_give_order.setOnClickListener {

            var intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("orderedCoffeeList", orderedCoffeeList)
            startActivity(intent)
        }
    }


    fun DataSource() : ArrayList<Coffee>
    {
        var coffeeNames = resources.getStringArray(R.array.CoffeeName)
        var coffeeImages = arrayOf(
            R.drawable.latte,
            R.drawable.macchiato,
            R.drawable.cappucino,
            R.drawable.turkish_coffee
        )
        var coffeePrices = resources.getStringArray(R.array.CoffeePrice)

        for(i in 0..coffeeImages.size-1)
        {
            var addedCoffee = Coffee(coffeeNames[i], coffeeImages[i], coffeePrices[i],"","","","")
            coffeeList.add(addedCoffee)
        }
        return coffeeList
    }

}


