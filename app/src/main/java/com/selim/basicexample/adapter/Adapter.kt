package com.selim.basicexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.selim.basicexample.Coffee
import com.selim.basicexample.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.coffee_row.view.*

class Adapter(var list:ArrayList<Coffee>):RecyclerView.Adapter<Adapter.CoffeeVH>() {

    var totalPrice = 0.0
    var total = MutableLiveData<Double>()
    var cart = MutableLiveData<ArrayList<Coffee>>()
    var orderedCoffeeList = ArrayList<Coffee>()
    var selectedCoffeeSize = "Küçük"
    var cream = ""
    var chocolateSyrup = ""
    var sugar = ""


    class CoffeeVH(itemView: View):RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeVH {
      var itemView=LayoutInflater.from(parent.context).inflate(R.layout.coffee_row,parent,false)
        return CoffeeVH(itemView)
    }

    override fun onBindViewHolder(holder: CoffeeVH, position: Int) {

        holder.itemView.tv_coffee_name.text = list.get(position).name
        holder.itemView.img_coffee.setImageResource(list.get(position).image)
        holder.itemView.tv_coffee_price.text = list.get(position).price
        holder.itemView.btn_add_coffee.setOnClickListener {

            var price:Double = list.get(position).price!!.toDouble()

            when(holder.itemView.rg_coffee_size.checkedRadioButtonId)
            {
                R.id.rb_small->price
                R.id.rb_medium->price+=2
                R.id.rb_large->price+=4
            }
            when(holder.itemView.rg_coffee_size.checkedRadioButtonId)
            {
                R.id.rb_small->selectedCoffeeSize = "Küçük"
                R.id.rb_medium->selectedCoffeeSize = "Orta"
                R.id.rb_large->selectedCoffeeSize = "Büyük"
            }
            if(holder.itemView.cb_cream.isChecked)
            {
                price+=2
                cream = "Kremalı"
            }
            else
            {
                cream = "Kremasız"
            }
            if(holder.itemView.cb_chocolate_syrup.isChecked)
            {
                price+=2
                chocolateSyrup = "Çikolata şuruplu"
            }
            else
            {
                chocolateSyrup = ""
            }
            if(holder.itemView.sw_sugar.isChecked)
            {
                price+=1
                sugar = "Şekerli"
            }
            else
            {
                sugar = "Şekersiz"
            }

            totalPrice += price
            total.value = totalPrice

            var coffee = Coffee(list.get(position).name ,list.get(position).image, list.get(position).price, selectedCoffeeSize,
                cream, chocolateSyrup, sugar)

            orderedCoffeeList.add(coffee)
            cart.value = orderedCoffeeList

            holder.itemView.cb_cream.isChecked=false
            holder.itemView.cb_chocolate_syrup.isChecked=false
            holder.itemView.sw_sugar.isChecked=false
            holder.itemView.rg_coffee_size.clearCheck()
        }
    }


    override fun getItemCount() = list.size

}


