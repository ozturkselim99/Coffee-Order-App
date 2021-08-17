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
    private var totalAmount: (Double) -> Unit,
    private var basketList: (ArrayList<Coffee>) -> Unit
) : RecyclerView.Adapter<CoffeeHomeAdapter.CoffeeVH>() {

    class CoffeeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val coffeeName = itemView.findViewById<TextView>(R.id.coffee_name)
        private val coffeeImage = itemView.findViewById<ImageView>(R.id.imageView)
        private val addToBasket = itemView.findViewById<Button>(R.id.siparis_ver)
        private val coffeeRadioGroup = itemView.findViewById<RadioGroup>(R.id.radio_group)
        private val coffeeSizeS = itemView.findViewById<RadioButton>(R.id.kucuk)
        private val coffeeSizeM = itemView.findViewById<RadioButton>(R.id.orta)
        private val coffeeSizeL = itemView.findViewById<RadioButton>(R.id.buyuk)
        private val coffeeSyrup = itemView.findViewById<CheckBox>(R.id.checkbox_1)
        private val coffeeCream = itemView.findViewById<CheckBox>(R.id.checkbox_2)
        private val coffeeSugar = itemView.findViewById<CheckBox>(R.id.switch2)
        private val coffeeAmount = itemView.findViewById<TextView>(R.id.adet)


        var totalPrice: Double = 0.0
        var selectedCoffeeSize: String = "Küçük"
        var price: Double = 0.0
        val _basket = arrayListOf<Coffee>()

        fun bind(
            coffee: Coffee,
            totalAmount: (Double) -> Unit,
            basketList: (ArrayList<Coffee>) -> Unit
        ) {
            coffeeName.text = coffee.name + coffee.price
            Glide.with(itemView.context).load(coffee.imageUrl).centerCrop().into(coffeeImage)
            addToBasket.setOnClickListener {

                price = coffee.price.toString().toDouble()
                //Seçimlere göre hesaplamalar
                when (coffeeRadioGroup.checkedRadioButtonId) {
                    coffeeSizeS.id -> price = coffee.price.toString().toDouble()
                    coffeeSizeM.id -> price += 2
                    coffeeSizeL.id -> price += 3
                }
                when (coffeeRadioGroup.checkedRadioButtonId) {
                    coffeeSizeS.id -> selectedCoffeeSize = "Küçük"
                    coffeeSizeM.id -> selectedCoffeeSize = "Orta"
                    coffeeSizeL.id -> selectedCoffeeSize = "Büyük"
                }
                when (coffeeSyrup.isChecked) {
                    true -> price += 1
                }
                when (coffeeCream.isChecked) {
                    true -> price += 1
                }
                when (coffeeSugar.isChecked) {
                    true -> price += 1
                }
                if (coffeeAmount.text.toString() != "" && coffeeAmount.text.toString() != "0") {
                    price = (coffeeAmount.text.toString().toDouble() * price)
                    totalPrice += price
                    totalAmount(totalPrice)

                    val coffee = Coffee()
                    coffee.imageUrl = coffee.imageUrl
                    coffee.price = price.toString()
                    coffee.coffeeSize = selectedCoffeeSize
                    coffee.name = coffee.name
                    _basket.add(coffee)
                    basketList(_basket)

                } else {
                    Toast.makeText(itemView.context, "Lütfen adet giriniz", Toast.LENGTH_LONG)
                        .show()
                }
                // Seçimleri Temizleme
                coffeeSyrup.isChecked = false
                coffeeSugar.isChecked = false
                coffeeAmount.setText("1")
                coffeeRadioGroup.clearCheck()
                coffeeSugar.isChecked = false
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
        holder.bind(coffeeList[position], { totalPrice ->
            totalAmount(totalPrice)
        }, { list ->
            basketList(list)
        })
    }

    override fun getItemCount() = coffeeList.size

}


