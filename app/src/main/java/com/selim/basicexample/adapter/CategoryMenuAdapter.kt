package com.selim.basicexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selim.basicexample.R
import com.selim.basicexample.model.CoffeeCategory
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_category_menu.view.*

class CategoryMenuAdapter(val categoryList:ArrayList<CoffeeCategory>) : RecyclerView.Adapter<CategoryMenuAdapter.CategoryMenuVH>() {
    class CategoryMenuVH(itemView:View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMenuVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_category_menu, parent, false)
        return CategoryMenuVH(itemView)
    }

    override fun onBindViewHolder(holder: CategoryMenuVH, position: Int) {
        Glide.with(holder.itemView.context).load(categoryList.get(position).imageUrl).centerCrop().into(holder.itemView.img_category_menu)
        holder.itemView.tv_category_menu_name.text = categoryList.get(position).name
    }

    override fun getItemCount() = categoryList.size
}