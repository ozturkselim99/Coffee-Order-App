/*package com.selim.basicexample.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.selim.basicexample.R
import com.selim.basicexample.adapter.CategoryAdapter
import com.selim.basicexample.adapter.CoffeeHomeAdapter
import com.selim.basicexample.model.Coffee
import com.selim.basicexample.model.CoffeeCategory

class MainActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null
    private val buttonSignOut: Button by lazy { findViewById(R.id.button_sign_out) }
    private val buttonAddress: Button by lazy { findViewById(R.id.btn_address) }
    private val buttonCategories: Button by lazy { findViewById(R.id.btn_categories) }
    private val recyclerViewProduct: RecyclerView by lazy { findViewById(R.id.recycler_view_product) }
    private val recyclerViewCategory: RecyclerView by lazy { findViewById(R.id.recycler_view_category) }
    private val list: Button by lazy { findViewById(R.id.list) }

    private var firebase: FirebaseFirestore? = null
    private var basketList = arrayListOf<Coffee>()
    private var totalBasket: Double = 0.0

    private val totalPriceTextView by lazy { findViewById<TextView>(R.id.total_price) }

    override fun onCreate(savedInstanceState: Bundle?) {

        firebase = FirebaseFirestore.getInstance()
        Log.d("MainActivityLIFECYCLE", "onCreate")
        checkUser()
        //örnek kategori id
        getCoffeesAsCategory("K0xlIQUECx8adOELyJay")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerViewProduct.layoutManager = layoutManager

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

        buttonAddress.setOnClickListener {
            val intent = Intent(this, AddressActivity::class.java)
            startActivity(intent)
        }

        buttonCategories.setOnClickListener {
            val intent = Intent(this, CoffeeCategoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        getCategories()
    }

    private fun getCategories() {
        firebase?.collection("category")?.get()?.addOnSuccessListener { snapshot ->
            val list = arrayListOf<CoffeeCategory>()

            snapshot.documents.forEach { documentSnapshot ->
                documentSnapshot.toObject(CoffeeCategory::class.java)?.let { category ->
                    category.id = documentSnapshot.id
                    list.add(category)
                }
            }
            loadCategories(list)
        }
    }

    private fun loadCategories(list: ArrayList<CoffeeCategory>) {
        val gridLayoutManager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        recyclerViewCategory.layoutManager = gridLayoutManager
        val categoryAdapter = CategoryAdapter(list) { categoryId ->
            getCoffeesAsCategory(categoryId)
        }
        recyclerViewCategory.adapter = categoryAdapter
    }

    private fun getCoffeesAsCategory(categoryId: String) {
        firebase?.collection("category")?.document(categoryId)?.collection("coffees")
            ?.addSnapshotListener { snapshot, error ->
                val list = ArrayList<Coffee>()
                snapshot?.documents?.forEach { documentSnapshot ->
                    documentSnapshot.toObject(Coffee::class.java)?.let { coffee ->
                        coffee.id = documentSnapshot.id
                        list.add(coffee)
                    }
                }
            }
    }


    private fun checkUser() {
        auth = Firebase.auth

        if (auth?.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
*/
