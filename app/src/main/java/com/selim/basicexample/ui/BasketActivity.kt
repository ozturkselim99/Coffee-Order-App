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
import com.selim.basicexample.adapter.AddressAdapter
import com.selim.basicexample.adapter.BasketAdapter
import com.selim.basicexample.model.Address
import com.selim.basicexample.model.Coffee

class BasketActivity : AppCompatActivity() {
    private var firestore: FirebaseFirestore? = null
    private var auth: FirebaseAuth? = null
    private val coffees = arrayListOf<Coffee>()
    private var totalPrice:Double=0.0


    private val recyclerViewBasket by lazy { findViewById<RecyclerView>(R.id.basketRecyclerView) }
    private  val totalBasket by lazy { findViewById<TextView>(R.id.total_basket) }

    override fun onResume() {
        super.onResume()
        getMyBasket()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

    }

    private fun getMyBasket() {
        auth?.currentUser?.uid?.let { userId ->
            firestore?.collection("user")?.whereEqualTo("userId", userId)
                    ?.addSnapshotListener { value, error ->

                        value?.documents?.firstOrNull()?.let { userDocumentId ->

                            userDocumentId.reference.collection("basket")
                                    .addSnapshotListener { snapshots, error ->

                                        snapshots?.documents?.forEach { snapshot ->
                                            snapshot.toObject(Coffee::class.java)
                                                    ?.let { basket ->
                                                        basket.id = snapshot.id
                                                        coffees.add(basket)
                                                    }
                                        }
                                        loadBasket(coffees)
                                    }
                        }
                    }
        }
    }

    private fun loadBasket(basketList: ArrayList<Coffee>) {
        val layoutManager= GridLayoutManager(this,2)
        recyclerViewBasket.layoutManager=layoutManager
        val adapter= BasketAdapter(basketList)
        recyclerViewBasket.adapter=adapter
        totalPrice(basketList)
    }

    private fun totalPrice(basketList:ArrayList<Coffee>)
    {
        basketList.forEach { coffee->
            totalPrice+=coffee.price.toString().toDouble()
        }
        totalBasket.text=totalPrice.toString()
    }

}