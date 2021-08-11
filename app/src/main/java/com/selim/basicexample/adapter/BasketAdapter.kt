package com.selim.basicexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selim.basicexample.R
import com.selim.basicexample.model.Coffee
import kotlinx.android.synthetic.main.basket_item_row.view.*

class BasketAdapter(val coffeeList:ArrayList<Coffee>): RecyclerView.Adapter<BasketAdapter.BasketAdapterVH>() {

    class BasketAdapterVH(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapterVH {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.basket_item_row, parent,false)
        return BasketAdapterVH(itemView)
    }

    override fun onBindViewHolder(holder: BasketAdapterVH, position: Int) {
        holder.itemView.basket_coffee_size.text=coffeeList.get(position).coffeeSize
        holder.itemView.basket_coffee_name.text=coffeeList.get(position).name
        holder.itemView.basket_coffee_price.text=coffeeList.get(position).price
        Glide.with(holder.itemView.context).load(coffeeList.get(position).imageUrl).centerCrop().into(holder.itemView.basket_coffee_image)
    }

    override fun getItemCount(): Int {
        return coffeeList.size
    }
}