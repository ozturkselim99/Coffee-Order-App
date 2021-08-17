package com.selim.basicexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selim.basicexample.R
import com.selim.basicexample.model.Coffee
import kotlinx.android.synthetic.main.item_coffee.view.*


class CoffeeHomeAdapter(private val coffeeList:ArrayList<Coffee>):RecyclerView.Adapter<CoffeeHomeAdapter.CoffeeVH>() {

    var totalPrice:Double=0.0
    var selectedCoffeeSize:String="Küçük"
    var price:Double=0.0
    val _basket= arrayListOf<Coffee>()
    val total=MutableLiveData<Double>()
    val basket=MutableLiveData<ArrayList<Coffee>>()

    class CoffeeVH(itemView: View):RecyclerView.ViewHolder(itemView) {

        private val coffeeName = itemView.findViewById<TextView>(R.id.coffee_name)
        private val coffeeImage=itemView.findViewById<ImageView>(R.id.imageView)

        fun bind(coffee: Coffee)
        {
            coffeeName.text=coffee.name+coffee.price
            Glide.with(itemView.context).load(coffee.imageUrl).centerCrop().into(coffeeImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeVH {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_coffee,parent,false)
        return CoffeeVH(itemView)
    }

    override fun onBindViewHolder(holder: CoffeeVH, position: Int) {
        holder.bind(coffeeList[position])

        holder.itemView.siparis_ver.setOnClickListener {
            price = coffeeList[position].price.toString().toDouble()
            //Seçimlere göre hesaplamalar
            when(holder.itemView.radio_group.checkedRadioButtonId){
                holder.itemView.kucuk.id->price= coffeeList[position].price.toString().toDouble()
                holder.itemView.orta.id->price+=2
                holder.itemView.buyuk.id->price+=3
            }
            when(holder.itemView.radio_group.checkedRadioButtonId){
                holder.itemView.kucuk.id->selectedCoffeeSize="Küçük"
                holder.itemView.orta.id->selectedCoffeeSize="Orta"
                holder.itemView.buyuk.id->selectedCoffeeSize="Büyük"
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
                coffee.imageUrl= coffeeList[position].imageUrl
                coffee.price=price.toString()
                coffee.coffeeSize=selectedCoffeeSize
                coffee.name= coffeeList[position].name
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
            price= coffeeList[position].price.toString().toDouble()
        }
    }

    override fun getItemCount()=coffeeList.size

}


