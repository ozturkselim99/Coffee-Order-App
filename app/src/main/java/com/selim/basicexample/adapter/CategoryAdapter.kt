package com.selim.basicexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selim.basicexample.R
import com.selim.basicexample.model.CoffeeCategory

class CategoryAdapter(
        private val categoryList: ArrayList<CoffeeCategory>,
        private val categoryClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    class CategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val categoryName = itemView.findViewById<TextView>(R.id.text_view_category_name)
        private val categoryImage = itemView.findViewById<ImageView>(R.id.image_view_category)

        fun bind(category:CoffeeCategory)
        {
            categoryName.text=category.name
            Glide.with(itemView.context).load(category.imageUrl).centerCrop().into(categoryImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryVH(itemView)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.bind(categoryList[position])
        holder.itemView.setOnClickListener {
            categoryClick(categoryList[position].id)
        }
    }

    override fun getItemCount() = categoryList.size

}