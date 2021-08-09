package com.selim.basicexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selim.basicexample.R
import com.selim.basicexample.model.Coffee
import kotlinx.android.synthetic.main.coffee_row.view.*

class CoffeeAdapter(val coffeeList:ArrayList<Coffee>):RecyclerView.Adapter<CoffeeAdapter.CoffeeVH>() {

    var totalPrice:Double=0.0
    var selectedCoffeSize:String="Küçük"
    var price:Double=0.0
    val _basket= arrayListOf<Coffee>()
    val  total=MutableLiveData<Double>()
    val basket=MutableLiveData<ArrayList<Coffee>>()

    class CoffeeVH(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeVH {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.coffee_row,parent,false)
        return CoffeeVH(itemView)
    }

    override fun onBindViewHolder(holder: CoffeeVH, position: Int) {
        holder.itemView.coffee_name.text=coffeeList.get(position).name+" "+coffeeList.get(position).price+"₺"
        //Glide Kullanımı
        Glide.with(holder.itemView.context).load(coffeeList.get(position).imageUrl).centerCrop().into(holder.itemView.imageView)
        holder.itemView.siparis_ver.setOnClickListener {
            price= coffeeList.get(position).price.toString().toDouble()
            //Seçimlere göre hesaplamalar
            when(holder.itemView.radio_group.checkedRadioButtonId){
                holder.itemView.kucuk.id->price=coffeeList.get(position).price.toString().toDouble()
                holder.itemView.orta.id->price+=2
                holder.itemView.buyuk.id->price+=3
            }
            when(holder.itemView.radio_group.checkedRadioButtonId){
                holder.itemView.kucuk.id->selectedCoffeSize="Küçük"
                holder.itemView.orta.id->selectedCoffeSize="Orta"
                holder.itemView.buyuk.id->selectedCoffeSize="Büyük"
            }

            when(holder.itemView.checkbox_1.isChecked){
                true->price+=1
            }

            when(holder.itemView.checkbox_2.isChecked){
                true->price+=1
            }
            when(holder.itemView.switch2.isChecked)
            {
                true->price+=1
            }
            if (holder.itemView.adet.text.toString()!=""&& holder.itemView.adet.text.toString()!="0")
            {
                price=(holder.itemView.adet.text.toString().toDouble()*price)
                totalPrice+=price
                total.value=totalPrice

                val coffee=Coffee()
                coffee.imageUrl=coffeeList.get(position).imageUrl
                coffee.price=price.toString()
                coffee.coffeeSize=selectedCoffeSize
                coffee.name=coffeeList.get(position).name
                _basket.add(coffee)
                basket.value=_basket
            }
            else
            {
                Toast.makeText(holder.itemView.context,"Lütfen adet giriniz",Toast.LENGTH_LONG).show()
            }

            // Seçimleri Temizleme
            holder.itemView.checkbox_1.isChecked=false
            holder.itemView.checkbox_2.isChecked=false
            holder.itemView.adet.setText("1")
            holder.itemView.radio_group.clearCheck()
            holder.itemView.switch2.isChecked=false
            price=coffeeList.get(position).price.toString().toDouble()
        }
    }
    override fun getItemCount(): Int {
        return coffeeList.size
    }
}


