package com.selim.basicexample.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.selim.basicexample.R
import com.selim.basicexample.adapter.CategoryAdapter
import com.selim.basicexample.adapter.CoffeeAdapter
import com.selim.basicexample.data.MockData
import com.selim.basicexample.model.Coffee
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null
    private val buttonSignOut: Button by lazy { findViewById(R.id.button_sign_out) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivityLIFECYCLE", "onCreate")

        checkUser()

        //Sepette gösterilecek ürünler
        var basketList = arrayListOf<Coffee>()

        //Toplam sepet miktarı
        var totalBasket: Double = 0.0

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Adapter
        val layoutManager = LinearLayoutManager(this)
        recycler_view_product.layoutManager = layoutManager

        val coffeeAdapter = CoffeeAdapter(MockData.getCoffeeList())
        recycler_view_product.adapter = coffeeAdapter

        val gridLayoutManager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        recycler_view_category.layoutManager = gridLayoutManager

        val categoryAdapter = CategoryAdapter(MockData.getCoffeeCategories())
        recycler_view_category.adapter = categoryAdapter

        //Adapter içindeki total değişkenimizi gözlemliyoruz. Değişkende bir değişiklik olduğunda activity_xml içindeki total_price textini değiştiriyoruz.
        coffeeAdapter.total.observe(this, Observer {
            total_price.text = "Toplam Tutar: " + it.toString() + "₺"
            totalBasket = it
        })
        coffeeAdapter.basket.observe(this, Observer {
            basketList = it
        })

        //Siparis listesine gitme ve veri yollama
        list.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            intent.putExtra("list", basketList)
            startActivity(intent)
        }

        buttonSignOut.setOnClickListener {
            auth?.signOut()
            checkUser()
        }

        btn_address.setOnClickListener {
            val intent = Intent(this, AddressActivity::class.java)
            startActivity(intent)
        }

        btn_categories.setOnClickListener {
            val intent = Intent(this, CoffeeCategoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkUser() {
        auth = Firebase.auth

        if (auth?.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivityLIFECYCLE", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivityLIFECYCLE", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivityLIFECYCLE", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivityLIFECYCLE", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivityLIFECYCLE", "onDestroy")
    }

}

