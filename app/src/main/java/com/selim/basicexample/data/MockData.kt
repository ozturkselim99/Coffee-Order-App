package com.selim.basicexample.data

import com.selim.basicexample.model.Coffee

object MockData {

    private val coffeList= arrayListOf(
        Coffee("Iced Coffee","14","https://www.starbucks.com.tr/media/buzlu-filtre-kahve_tcm95-2007_w1024_n.png"),
        Coffee("Caffè Misto","15","https://www.starbucks.com.tr/media/caffe-misto_tcm95-1988_w1024_n.png"),
        Coffee("Cold Brew Latte","16","https://www.starbucks.com.tr/media/sutlu-cold-brew_tcm95-20069_w1024_n.png"),
        Coffee("Cold Brew","17","https://www.starbucks.com.tr/media/cold-brew_tcm95-20068_w1024_n.png"),)

    fun getMockData(): ArrayList<Coffee> {
        return coffeList
    }
}