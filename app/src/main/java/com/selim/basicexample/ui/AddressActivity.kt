package com.selim.basicexample.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.selim.basicexample.AddNewAddressActivity
import com.selim.basicexample.R
import com.selim.basicexample.adapter.AddressAdapter
import com.selim.basicexample.model.Address

class AddressActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null
    private val recyclerViewAddress by lazy { findViewById<RecyclerView>(R.id.recycler_view_address) }
    private val buttonAddAddress by lazy { findViewById<FloatingActionButton>(R.id.fab_add) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val layoutManager = LinearLayoutManager(this)
        recyclerViewAddress.layoutManager = layoutManager


        buttonAddAddress.setOnClickListener {
            val intent = Intent(this, AddNewAddressActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        getAddresses()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_activity, menu)
        return true
    }

    private fun getAddresses() {
        auth?.currentUser?.uid?.let { userId ->
            firestore?.collection("user")?.whereEqualTo("userId", userId)
                ?.addSnapshotListener { value, error ->

                    value?.documents?.firstOrNull()?.let { userDocumentId ->

                        userDocumentId.reference.collection("address")
                            .addSnapshotListener { snapshots, error ->

                                val addresses = arrayListOf<Address>()

                                snapshots?.documents?.forEach { snapshot ->
                                    snapshot.toObject(Address::class.java)
                                        ?.let { address ->
                                            address.id = snapshot.id
                                            addresses.add(address)
                                        }
                                }

                                loadAddresses(addresses)
                            }


                    }
                }
        }
    }

    private fun loadAddresses(addresses: ArrayList<Address>) {
        val addressAdapter = AddressAdapter(addresses){addressId->
            addressDelete(addressId)
        }
        recyclerViewAddress.adapter = addressAdapter
    }

    private fun addressDelete(addressId:String)
    {
        auth?.currentUser?.uid?.let { userId ->
            firestore?.collection("user")?.whereEqualTo("userId", userId)
                ?.addSnapshotListener { value, error ->
                    value?.documents?.firstOrNull()?.let { userDocumentId ->
                        userDocumentId.reference.collection("address").document(addressId).delete().addOnCompleteListener { task->
                            when (task.isSuccessful) {
                                true -> {
                                    Toast.makeText(
                                        this,
                                        "Adres Silindi..",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                false -> Toast.makeText(
                                    this,
                                    "Adres Silinemedi..",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
        }

    }

}