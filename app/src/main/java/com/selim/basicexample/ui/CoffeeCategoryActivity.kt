package com.selim.basicexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.selim.basicexample.R
import com.selim.basicexample.adapter.CategoryMenuAdapter
import com.selim.basicexample.data.MockData
import com.selim.basicexample.model.CoffeeCategory
import kotlinx.android.synthetic.main.activity_coffee_category.*

class CoffeeCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee_category)

        val linearLayout = LinearLayoutManager(this)
        recycler_view_category_menu.layoutManager = linearLayout

        val categoryMenuAdapter = CategoryMenuAdapter(MockData.getCoffeeCategories())
        recycler_view_category_menu.adapter = categoryMenuAdapter

    }
}