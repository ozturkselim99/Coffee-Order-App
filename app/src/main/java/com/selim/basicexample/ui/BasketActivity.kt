package com.selim.basicexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
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
    private var totalPrice:Double=0.0

    private val recyclerViewBasket by lazy { findViewById<RecyclerView>(R.id.basketRecyclerView) }
    private  val totalBasket by lazy { findViewById<TextView>(R.id.total_basket) }
    private  val checkBasket by lazy { findViewById<TextView>(R.id.checkBasket) }

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
                                         val coffees = arrayListOf<Coffee>()
                                        snapshots?.documents?.forEach { snapshot ->
                                            snapshot.toObject(Coffee::class.java)
                                                    ?.let { basket ->
                                                        basket.id = snapshot.id
                                                        coffees.add(basket)
                                                    }
                                        }
                                        loadBasket(coffees)
                                        totalPrice(coffees)
                                    }
                        }
                    }
        }
    }

    private fun loadBasket(basketList: ArrayList<Coffee>) {
        basketIsEmpty(basketList)
        val layoutManager= GridLayoutManager(this,2)
        recyclerViewBasket.layoutManager=layoutManager
        val adapter= BasketAdapter(basketList){coffeeId->
            coffeeBasketDelete(coffeeId)
        }
        recyclerViewBasket.adapter=adapter
    }

    private fun totalPrice(basketList:ArrayList<Coffee>)
    {
        basketList.forEach { coffee->
            totalPrice+=coffee.price.toString().toDouble()
        }
        totalBasket.text = "Toplam Tutar: " + totalPrice.toString() + " ₺"
    }

    private fun coffeeBasketDelete(coffeeId:String)
    {
        auth?.currentUser?.uid?.let { userId ->
            firestore?.collection("user")?.whereEqualTo("userId", userId)
                ?.addSnapshotListener { value, error ->
                    value?.documents?.firstOrNull()?.let { userDocumentId ->
                        userDocumentId.reference.collection("basket").document(coffeeId).delete().addOnCompleteListener { task->
                            when (task.isSuccessful) {
                                true -> {
                                    Toast.makeText(
                                        this,
                                        "Sepetten Silindi..",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                false -> Toast.makeText(
                                    this,
                                    "Sepetten Silinemedi..",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
        }
        totalPrice=0.0
    }

    private fun basketIsEmpty(basketList: ArrayList<Coffee>)
    {
        if (!basketList.isEmpty())
        {
            checkBasket.visibility=View.GONE
        }
    }
}