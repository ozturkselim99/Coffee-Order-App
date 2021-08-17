package com.selim.basicexample.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.selim.basicexample.AddNewAddressActivity
import com.selim.basicexample.R
import com.selim.basicexample.model.Address

class AddressAdapter(private val addressList: MutableList<Address>) :
    RecyclerView.Adapter<AddressAdapter.AddressVH>() {
    class AddressVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val addressName = itemView.findViewById<TextView>(R.id.tv_address_name)
        private val addressInfo = itemView.findViewById<TextView>(R.id.tv_address_info)

        fun bind(address: Address) {
            addressName.text = address.addressName
            addressInfo.text =
                address.city + " " + address.district + " " +
                        address.avenue + " " + address.neighborhood + " " + address.street + " " +
                        address.buildingNumber + " " + address.number

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressVH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_address, parent, false)
        return AddressVH(itemView)
    }

    override fun onBindViewHolder(holder: AddressVH, position: Int) {
        holder.bind(addressList[position])
        holder.itemView.setOnClickListener {
            val updateIntent = Intent(holder.itemView.context, AddNewAddressActivity::class.java)
            updateIntent.putExtra("ADDRESS", addressList[position])
            holder.itemView.context.startActivity(updateIntent)
        }
    }

    override fun getItemCount() = addressList.size
}