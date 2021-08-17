package com.selim.basicexample.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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

class CoffeeAdapter(private val categoryId:String, private val coffeeList:ArrayList<Coffee>): RecyclerView.Adapter<CoffeeAdapter.CoffeeVH>() {

    class CoffeeVH(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val coffeeName = itemView.findViewById<TextView>(R.id.kahve_adi_txt)
        private val coffeeCategory = itemView.findViewById<TextView>(R.id.kahve_kategorisi_txt)
        private val coffeePrice= itemView.findViewById<TextView>(R.id.kahve_fiyati_txt)
        private val coffeeImage=itemView.findViewById<ImageView>(R.id.basket_coffee_image)

        fun bind(coffee:Coffee)
        {
            coffeeName.text=coffee.name
            //todo:burada kategori ismini nasıl gösterebiliriz?
            coffeePrice.text=coffee.price
            Glide.with(itemView.context).load(coffee.imageUrl).centerCrop().into(coffeeImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeVH {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.coffee_item_row,parent,false)
        return CoffeeVH(itemView)
    }

    override fun onBindViewHolder(holder: CoffeeVH, position: Int) {
        holder.bind(coffeeList[position])

        holder.itemView.item_coffee.setOnClickListener {
            val intent = Intent(holder.itemView.context, AddNewCoffeeActivity::class.java)
            intent.putExtra("COFFEE", coffeeList[position])
            intent.putExtra("CATEGORY_ID", categoryId)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = coffeeList.size

}