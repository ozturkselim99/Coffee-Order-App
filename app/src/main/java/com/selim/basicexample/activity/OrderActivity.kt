package com.selim.basicexample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.selim.basicexample.Coffee
import com.selim.basicexample.adapter.CartAdapter
import com.selim.basicexample.R
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.order_row.*

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        var orderedCoffeeList = intent.getParcelableArrayListExtra<Coffee>("orderedCoffeeList")
        var totalCart:Double = 0.0

        if(orderedCoffeeList != null)
        {
            orderedCoffeeList.forEach{
                totalCart+=it.price!!.toDouble()
            }
            tv_total_price.text = totalCart.toString()

            var layoutManager = GridLayoutManager(this,2)
            recyclerViewOrder.layoutManager = layoutManager

            var adapter = CartAdapter(orderedCoffeeList)
            recyclerViewOrder.adapter = adapter
        }
    }
}