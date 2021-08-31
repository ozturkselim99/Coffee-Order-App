package com.selim.basicexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selim.basicexample.R
import com.selim.basicexample.model.Coffee

class CoffeeHomeAdapter(
    private val coffeeList: ArrayList<Coffee>,
    private val itemSelected: (Coffee) -> Unit
) : RecyclerView.Adapter<CoffeeHomeAdapter.CoffeeVH>() {

    class CoffeeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val coffeeName = itemView.findViewById<TextView>(R.id.tv_coffee_name)
        private val coffeeImage = itemView.findViewById<ImageView>(R.id.img_coffee_item)
        private val coffeePrice = itemView.findViewById<TextView>(R.id.tv_coffee_price)
        private val addToBasket = itemView.findViewById<Button>(R.id.btn_add_order)
        private val coffeeRadioGroup = itemView.findViewById<RadioGroup>(R.id.radio_group)
        private val coffeeSizeS = itemView.findViewById<RadioButton>(R.id.small)
        private val coffeeSizeM = itemView.findViewById<RadioButton>(R.id.medium)
        private val coffeeSizeL = itemView.findViewById<RadioButton>(R.id.large)
        private val coffeeSyrup = itemView.findViewById<CheckBox>(R.id.cb_chocolate_syrup)
        private val coffeeCream = itemView.findViewById<CheckBox>(R.id.cb_cream)
        private val coffeeDecaf = itemView.findViewById<Switch>(R.id.sw_decaf)
        private val coffeeAmount = itemView.findViewById<TextView>(R.id.et_amount_coffee)


        var totalPrice: Double = 0.0
        var selectedCoffeeSize: String = "Küçük"
        var price: Double = 0.0
        var cream: String = ""
        var chocolateSyrup: String = ""
        var decaf: String = ""
        val _basket = arrayListOf<Coffee>()

        fun bind(
            coffee: Coffee,
            itemSelected: (Coffee) -> Unit
        ) {
            coffeeName.text = coffee.name
            coffeePrice.text = coffee.price + " TL"
            Glide.with(itemView.context).load(coffee.imageUrl).centerCrop().into(coffeeImage)
            addToBasket.setOnClickListener {

                price = coffee.price.toString().toDouble()
                //Seçimlere göre hesaplamalar
                when(coffeeRadioGroup.checkedRadioButtonId) {
                    coffeeSizeS.id -> price = coffee.price.toString().toDouble()
                    coffeeSizeM.id -> price += 2
                    coffeeSizeL.id -> price += 3
                }
                when(coffeeRadioGroup.checkedRadioButtonId) {
                    coffeeSizeS.id -> selectedCoffeeSize = "Küçük"
                    coffeeSizeM.id -> selectedCoffeeSize = "Orta"
                    coffeeSizeL.id -> selectedCoffeeSize = "Büyük"
                }
                if(coffeeSyrup.isChecked) {
                    price += 1
                    chocolateSyrup = "Çikolata Şuruplu, "
                }
                if(coffeeCream.isChecked) {
                    price += 1
                    cream = "Kremalı, "
                }
                if(coffeeDecaf.isChecked) {
                    price += 1
                    decaf = "Kafeinli"
                }
                if(coffeeAmount.text.toString() != "" && coffeeAmount.text.toString() != "0") {
                    price = (coffeeAmount.text.toString().toDouble() * price)
                    totalPrice += price

                    val newcoffee = coffee
                    newcoffee.coffeeSize = selectedCoffeeSize
                    newcoffee.cream = cream
                    newcoffee.chocolateSyrup = chocolateSyrup
                    newcoffee.decaf = decaf

                    _basket.add(newcoffee)
                    itemSelected(newcoffee)

                } else {
                    Toast.makeText(itemView.context, "Lütfen adet giriniz", Toast.LENGTH_LONG)
                        .show()
                }
                // Seçimleri Temizleme
                coffeeSyrup.isChecked = false
                coffeeDecaf.isChecked = false
                coffeeAmount.setText("1")
                coffeeRadioGroup.clearCheck()
                coffeeDecaf.isChecked = false
                price = coffee.price.toString().toDouble()

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeVH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coffee, parent, false)
        return CoffeeVH(itemView)
    }

    override fun onBindViewHolder(holder: CoffeeVH, position: Int) {
        holder.bind(coffeeList[position]) { newcoffee ->
            itemSelected(newcoffee)
        }
    }

    override fun getItemCount() = coffeeList.size

}


