package com.selim.basicexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.selim.basicexample.R
import com.selim.basicexample.adapter.BasketAdapter
import com.selim.basicexample.model.Coffee
import kotlinx.android.synthetic.main.activity_basket.*

class BasketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        var _totalBasket:Double=0.0
        val basketList=getIntent().getSerializableExtra("list") as ArrayList<Coffee>

        if (basketList != null) {
            basketList.forEach {
                _totalBasket+=it.price!!.toDouble()
            }
            total_basket.text="Toplam Tutar "+_totalBasket.toString()+"₺"
            val layoutManager= GridLayoutManager(this,2)
            basketRecyclerView.layoutManager=layoutManager

            val adapter= BasketAdapter(basketList)
            basketRecyclerView.adapter=adapter
        }
    }
}