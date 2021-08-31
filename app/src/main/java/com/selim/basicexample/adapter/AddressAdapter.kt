package com.selim.basicexample.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.selim.basicexample.AddNewAddressActivity
import com.selim.basicexample.R
import com.selim.basicexample.model.Address

class AddressAdapter(private val addressList: MutableList<Address>,private val addressId: (String) -> Unit) :
    RecyclerView.Adapter<AddressAdapter.AddressVH>() {

    class AddressVH(itemView: View,private val addressId: (String) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val addressName = itemView.findViewById<TextView>(R.id.tv_address_name)
        private val addressInfo = itemView.findViewById<TextView>(R.id.tv_address_info)
        private val addressLayout = itemView.findViewById<ConstraintLayout>(R.id.address)
        private val addressDelete = itemView.findViewById<ImageView>(R.id.address_delete)

        fun bind(address: Address) {
            addressName.text = address.addressName
            addressInfo.text =
                address.city + " " + address.state + " " +
                        address.avenue + " " + address.neighborhood + " " + address.street + " " +
                        address.buildingNumber + " " + address.number
            addressLayout.setOnClickListener {
                val updateIntent = Intent(itemView.context, AddNewAddressActivity::class.java)
                updateIntent.putExtra("ADDRESS", address)
                itemView.context.startActivity(updateIntent)
            }
            addressDelete.setOnClickListener {
                addressId(address.id)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressVH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_address, parent, false)
        return AddressVH(itemView,addressId)
    }

    override fun onBindViewHolder(holder: AddressVH, position: Int) {
        holder.bind(addressList[position])
    }

    override fun getItemCount() = addressList.size
}