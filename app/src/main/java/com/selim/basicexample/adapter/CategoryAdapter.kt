package com.selim.basicexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selim.basicexample.R
import com.selim.basicexample.model.CoffeeCategory
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(
    val categoryList: ArrayList<CoffeeCategory>,
    private val categoryClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {
    class CategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryVH(itemView)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val category = categoryList[position]
        Glide.with(holder.itemView.context).load(category.imageUrl).centerCrop()
            .into(holder.itemView.image_view_category)
        holder.itemView.text_view_category_name.text = category.name

        holder.itemView.setOnClickListener {
            categoryClick(category.id)
        }
    }

    override fun getItemCount() = categoryList.size
}