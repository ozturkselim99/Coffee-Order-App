package com.selim.basicexample.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.selim.basicexample.AddNewAddressActivity
import com.selim.basicexample.R
import com.selim.basicexample.model.Address
import com.selim.basicexample.ui.BasketActivity
import kotlinx.android.synthetic.main.item_address.view.*

class AddressAdapter(val addressList: MutableList<Address>) :
    RecyclerView.Adapter<AddressAdapter.AddressVH>() {
    class AddressVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressVH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_address, parent, false)
        return AddressVH(itemView)
    }

    override fun onBindViewHolder(holder: AddressVH, position: Int) {
        holder.itemView.tv_address_name.text = addressList.get(position).addressName
        holder.itemView.tv_address_info.text =
            addressList.get(position).city + " " + addressList.get(position).district + " " +
                    addressList.get(position).avenue + " " + addressList.get(position).neighborhood + " " + addressList.get(
                position
            ).street + " " +
                    addressList.get(position).buildingNumber + " " + addressList.get(position).number

        holder.itemView.address.setOnClickListener {
           val updateIntent=Intent(holder.itemView.context,AddNewAddressActivity::class.java)
            updateIntent.putExtra("address", addressList.get(position))
           holder.itemView.context.startActivity(updateIntent)
        }
    }

    override fun getItemCount() = addressList.size
}