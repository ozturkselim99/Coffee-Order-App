package com.selim.basicexample

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.coffee_row.view.*

class Adapter(var list:ArrayList<Coffee>):RecyclerView.Adapter<Adapter.CoffeeVH>() {

    class CoffeeVH(itemView: View):RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeVH {
      var itemView=LayoutInflater.from(parent.context).inflate(R.layout.coffee_row,parent,false)
        return CoffeeVH(itemView)
    }

    override fun onBindViewHolder(holder: CoffeeVH, position: Int) {
        holder.itemView.tv_coffee_name.text = list.get(position).name
        holder.itemView.img_coffee.setImageResource(list.get(position).image)
        holder.itemView.tv_coffee_fee.text = list.get(position).fee
    }

    override fun getItemCount(): Int {
        return list.size
    }

}


