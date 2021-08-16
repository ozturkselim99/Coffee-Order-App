package com.selim.basicexample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.selim.basicexample.AddNewAddressActivity
import com.selim.basicexample.R
import com.selim.basicexample.adapter.AddressAdapter
import com.selim.basicexample.model.Address
import kotlinx.android.synthetic.main.activity_address.*

class AddressActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val layoutManager = LinearLayoutManager(this)
        recycler_view_address.layoutManager = layoutManager


        fab_add.setOnClickListener {
            val intent = Intent(this, AddNewAddressActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        getAddresses()
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
        val addressAdapter = AddressAdapter(addresses)
        recycler_view_address.adapter = addressAdapter
    }
}