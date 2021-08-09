package com.selim.basicexample.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.selim.basicexample.R
import com.selim.basicexample.adapter.CoffeeAdapter
import com.selim.basicexample.data.MockData
import com.selim.basicexample.model.Coffee
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        //Sepette gösterilecek ürünler
        var basketList= arrayListOf<Coffee>()

        //Toplam sepet miktarı
        var totalBasket:Double=0.0

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Adapter
        val layoutManager=LinearLayoutManager(this)
        recycler_view_product.layoutManager=layoutManager

        val adapter= CoffeeAdapter(MockData.getCoffeList())
        recycler_view_product.adapter=adapter

        //Adapter içindeki total değişkenimizi gözlemliyoruz. Değişkende bir değişiklik olduğunda activity_xml içindeki total_price textini değiştiriyoruz.
        adapter.total.observe(this, Observer {
            total_price.text="Toplam Tutar: "+it.toString()+"₺"
            totalBasket=it
        })
        adapter.basket.observe(this, Observer {
            basketList=it
        })

        //Siparis listesine gitme ve veri yollama
        list.setOnClickListener {
            val intent=Intent(this, BasketActivity::class.java)
            intent.putExtra("list",basketList)
            startActivity(intent)
        }
    }
}

