package com.selim.basicexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.selim.basicexample.R
import com.selim.basicexample.adapter.BasketAdapter
import com.selim.basicexample.model.Coffee

class BasketActivity : AppCompatActivity() {
    private var firestore: FirebaseFirestore? = null
    private var auth: FirebaseAuth? = null

    override fun onResume() {
        super.onResume()
        getMyBasket()
    }

    private fun getMyBasket() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val recyclerViewBasket by lazy { findViewById<RecyclerView>(R.id.basketRecyclerView) }
        val totalBasket by lazy { findViewById<TextView>(R.id.total_basket) }

        var _totalBasket:Double=0.0

        if (basketList != null) {
            basketList.forEach {
                _totalBasket+=it.price!!.toDouble()
            }
            totalBasket.text="Toplam Tutar "+_totalBasket.toString()+"₺"
            val layoutManager= GridLayoutManager(this,2)
            recyclerViewBasket.layoutManager=layoutManager

            val adapter= BasketAdapter(basketList)
            recyclerViewBasket.adapter=adapter
        }
    }
}