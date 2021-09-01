package com.selim.basicexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selim.basicexample.R
import com.selim.basicexample.model.Coffee

class BasketAdapter(private val coffeeList:ArrayList<Coffee>,  private val deleteCoffeeId: (String) -> Unit): RecyclerView.Adapter<BasketAdapter.BasketAdapterVH>() {

    class BasketAdapterVH(itemView: View,private val deleteCoffeeId: (String) -> Unit):RecyclerView.ViewHolder(itemView){

        private val basketCoffeeName = itemView.findViewById<TextView>(R.id.basket_coffee_name)
        private val basketCoffeeSize = itemView.findViewById<TextView>(R.id.basket_coffee_size)
        private val basketCoffeePrice = itemView.findViewById<TextView>(R.id.basket_coffee_price)
        private val basketCoffeeImage = itemView.findViewById<ImageView>(R.id.basket_coffee_image)
        private val basketCoffeeExtra = itemView.findViewById<TextView>(R.id.basket_extra)
        private val basketCoffeeDelete = itemView.findViewById<ImageView>(R.id.basket_coffee_delete)

        fun bind(coffee: Coffee)
        {
            basketCoffeeName.text = coffee.name
            basketCoffeeSize.text = coffee.coffeeSize
            basketCoffeePrice.text = coffee.price + " ₺"
            basketCoffeeExtra.text = "Ekstralar: " + coffee.cream + coffee.chocolateSyrup + coffee.soft
            Glide.with(itemView.context).load(coffee.imageUrl).centerCrop().into(basketCoffeeImage)
            basketCoffeeDelete.setOnClickListener {
                deleteCoffeeId(coffee.id.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapterVH {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.item_basket, parent,false)
        return BasketAdapterVH(itemView,deleteCoffeeId)
    }

    override fun onBindViewHolder(holder: BasketAdapterVH, position: Int) {
        holder.bind(coffeeList[position])

    }

    override fun getItemCount() = coffeeList.size

}