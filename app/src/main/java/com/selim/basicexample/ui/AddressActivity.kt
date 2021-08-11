package com.selim.basicexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selim.basicexample.R
import com.selim.basicexample.adapter.AddressAdapter
import com.selim.basicexample.data.MockData
import kotlinx.android.synthetic.main.activity_address.*

class AddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        val layoutManager = LinearLayoutManager(this)
        recycler_view_address.layoutManager = layoutManager

        val addressAdapter = AddressAdapter(MockData.getAddressList())
        recycler_view_address.adapter = addressAdapter
    }
}