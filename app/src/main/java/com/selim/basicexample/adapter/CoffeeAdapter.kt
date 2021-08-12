package com.selim.basicexample.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.selim.basicexample.R
import com.selim.basicexample.data.MockData
import com.selim.basicexample.model.Coffee
import com.selim.basicexample.model.CoffeeCategory
import com.selim.basicexample.ui.AddNewCoffeeActivity
import kotlinx.android.synthetic.main.coffee_item_row.*
import kotlinx.android.synthetic.main.coffee_item_row.view.*

var firestore : FirebaseFirestore? = null

class CoffeeAdapter(val coffeeList:ArrayList<Coffee>): RecyclerView.Adapter<CoffeeAdapter.CoffeeVH>() {

    class CoffeeVH(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeVH {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.coffee_item_row,parent,false)
        return CoffeeVH(itemView)
    }

    override fun onBindViewHolder(holder: CoffeeVH, position: Int) {
        firestore = FirebaseFirestore.getInstance()

        val getIntent = Intent()
        val categoryId = getIntent.getStringExtra("CATEGORY_ID") ?: ""

        Glide.with(holder.itemView.context).load(coffeeList.get(position).imageUrl).centerCrop().into(holder.itemView.kahve_foto)
        holder.itemView.kahve_adi_txt.text = coffeeList.get(position).name
        holder.itemView.kahve_fiyati_txt.text = coffeeList.get(position).price+"TL"
        holder.itemView.kahve_kategorisi_txt.text = categoryId //burada kategori ismini nasıl gösterebiliriz?

        holder.itemView.item_coffee.setOnClickListener {
            val intent = Intent(holder.itemView.context, AddNewCoffeeActivity::class.java)
            intent.putExtra("coffee", coffeeList.get(position))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = coffeeList.size

}