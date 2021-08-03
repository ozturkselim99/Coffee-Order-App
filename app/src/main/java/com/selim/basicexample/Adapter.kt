package com.selim.basicexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.coffee_row.view.*

class Adapter(var list:ArrayList<Coffee>):RecyclerView.Adapter<Adapter.CoffeeVH>() {

    var totalPrice = 0.0
    var total = MutableLiveData<Double>()

    class CoffeeVH(itemView: View):RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeVH {
      var itemView=LayoutInflater.from(parent.context).inflate(R.layout.coffee_row,parent,false)
        return CoffeeVH(itemView)
    }

    override fun onBindViewHolder(holder: CoffeeVH, position: Int) {

        var price:Double = list.get(position).price.toDouble()

        holder.itemView.tv_coffee_name.text = list.get(position).name
        holder.itemView.img_coffee.setImageResource(list.get(position).image)
        holder.itemView.tv_coffee_price.text = list.get(position).price
        holder.itemView.btn_add_coffee.setOnClickListener {

            when(holder.itemView.rg_coffee_size.checkedRadioButtonId)
            {
                R.id.rb_small->price
                R.id.rb_medium->price+=2
                R.id.rb_large->price+=4
            }
            if(holder.itemView.cb_cream.isChecked)
            {
                price+=2
            }
            if(holder.itemView.cb_chocolate_syrup.isChecked)
            {
                price+=2
            }
            if(holder.itemView.sw_sugar.isChecked)
            {
                price+=1
            }

            totalPrice += price
            total.value = totalPrice


            holder.itemView.cb_cream.isChecked=false
            holder.itemView.cb_chocolate_syrup.isChecked=false
            holder.itemView.sw_sugar.isChecked=false
            holder.itemView.rg_coffee_size.clearCheck()

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }



}


