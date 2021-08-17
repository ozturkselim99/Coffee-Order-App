package com.selim.basicexample.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selim.basicexample.R
import com.selim.basicexample.model.CoffeeCategory
import com.selim.basicexample.ui.CoffeesActivity

class CategoryMenuAdapter(private val context: Context, private val categoryList:ArrayList<CoffeeCategory>) : RecyclerView.Adapter<CategoryMenuAdapter.CategoryMenuVH>() {

    class CategoryMenuVH(itemView:View) : RecyclerView.ViewHolder(itemView) {

        private val categoryName = itemView.findViewById<TextView>(R.id.tv_category_menu_name)
        private val categoryImage = itemView.findViewById<ImageView>(R.id.img_category_menu)

        fun bind(category:CoffeeCategory)
        {
            categoryName.text=category.name
            Glide.with(itemView.context).load(category.imageUrl).centerCrop().into(categoryImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMenuVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_category_menu, parent, false)
        return CategoryMenuVH(itemView)
    }

    override fun onBindViewHolder(holder: CategoryMenuVH, position: Int) {
        holder.bind(categoryList[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(context, CoffeesActivity::class.java)
            intent.putExtra("CATEGORY_ID", categoryList[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = categoryList.size
}