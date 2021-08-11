package com.selim.basicexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selim.basicexample.R
import com.selim.basicexample.model.CoffeeCategory
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(val categoryList:ArrayList<CoffeeCategory>): RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {
    class CategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        return CategoryVH(itemView)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        Glide.with(holder.itemView.context).load(categoryList.get(position).imageUrl).centerCrop().into(holder.itemView.image_view_category)
        holder.itemView.text_view_category_name.text = categoryList.get(position).name
    }

    override fun getItemCount() = categoryList.size
}