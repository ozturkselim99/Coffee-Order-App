package com.selim.basicexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selim.basicexample.R
import com.selim.basicexample.data.MockData
import com.selim.basicexample.model.Coffee
import kotlinx.android.synthetic.main.coffee_item_row.view.*

class CoffeeAdapter(val coffeeList:ArrayList<Coffee>): RecyclerView.Adapter<CoffeeAdapter.CoffeeVH>() {
    class CoffeeVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeVH {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.coffee_item_row,parent,false)
        return CoffeeVH(itemView)
    }

    override fun onBindViewHolder(holder: CoffeeVH, position: Int) {
        Glide.with(holder.itemView.context).load(coffeeList.get(position).imageUrl).centerCrop().into(holder.itemView.kahve_foto)
        holder.itemView.kahve_adi_txt.text = coffeeList.get(position).name
        holder.itemView.kahve_fiyati_txt.text = coffeeList.get(position).price+"TL"
        holder.itemView.kahve_kategorisi_txt.text=getCoffeeCategory(coffeeList.get(position).categoryId.toString())
    }

    override fun getItemCount() = coffeeList.size


    private fun getCoffeeCategory(categoryId:String):String
    {
        return MockData.getCoffeeCategories().find {it.id==categoryId}?.name.toString()
    }
}