package com.selim.basicexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selim.basicexample.Coffee
import com.selim.basicexample.R
import kotlinx.android.synthetic.main.order_row.view.*

class CartAdapter(var orderedCoffeeList:ArrayList<Coffee>): RecyclerView.Adapter<CartAdapter.CartVH>() {

    class CartVH(itemView:View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartVH {
        var itemView= LayoutInflater.from(parent.context).inflate(R.layout.order_row,parent,false)
        return CartVH(itemView)
    }

    override fun onBindViewHolder(holder: CartVH, position: Int) {
        holder.itemView.tv_order_coffee_name.text = orderedCoffeeList.get(position).name
        holder.itemView.tv_order_coffee_size.text = orderedCoffeeList.get(position).size
        holder.itemView.tv_order_coffee_extras.text = orderedCoffeeList.get(position).cream + " " + orderedCoffeeList.get(position).chocolateSyrup
        holder.itemView.tv_order_coffee_sugar.text = orderedCoffeeList.get(position).sugar
        holder.itemView.tv_order_coffee_price.text = orderedCoffeeList.get(position).price
        holder.itemView.img_order_coffee.setImageResource(orderedCoffeeList.get(position).image)
    }

    override fun getItemCount() = orderedCoffeeList.size

}