package com.selim.basicexample.model

class CoffeeCategory(

    var id: String = "",
    var name: String = "",
    var imageUrl: String = "",

    ) {
    override fun toString(): String {
        return name
    }
}



