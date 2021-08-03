package com.selim.basicexample

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.coffee_row.view.*

class CoffeeAdapter(val coffeeList:ArrayList<MainActivity.Coffe>):RecyclerView.Adapter<CoffeeAdapter.CoffeeVH>() {

    var totalPrice:Double=0.0
    val  total=MutableLiveData<Double>()

    class CoffeeVH(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeVH {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.coffee_row,parent,false)
        return CoffeeVH(itemView)
    }

    override fun onBindViewHolder(holder: CoffeeVH, position: Int) {
        var price:Double= coffeeList.get(position).price.toDouble()
        holder.itemView.coffee_name.text=coffeeList.get(position).name+" "+coffeeList.get(position).price

        //Glide Kullanımı
        Glide.with(holder.itemView.context).load(coffeeList.get(position).imageUrl).centerCrop().into(holder.itemView.imageView)
        holder.itemView.siparis_ver.setOnClickListener {

            //Seçimlere göre hesaplamalar
            when(holder.itemView.radio_group.checkedRadioButtonId){
                1->price=coffeeList.get(position).price.toDouble()
                2->price+=2
                3->price+=3
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

            if (holder.itemView.adet.text.toString()!="")
            {
                price=(holder.itemView.adet.text.toString().toDouble()*price)
            }

            //Global toplam fiyat değişkenine atama
            totalPrice+=price
            total.value=totalPrice
            Log.d("message",totalPrice.toString())

            // Seçimleri Temizleme
            holder.itemView.checkbox_1.isChecked=false
            holder.itemView.checkbox_2.isChecked=false
            holder.itemView.adet.text=null
            holder.itemView.radio_group.clearCheck()
            holder.itemView.switch2.isChecked=false
            price=0.0


        }


    }
    override fun getItemCount(): Int {
        return coffeeList.size
    }

}


