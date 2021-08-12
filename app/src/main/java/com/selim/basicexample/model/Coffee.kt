package com.selim.basicexample.model

import java.io.Serializable

class Coffee(
    var id: String? ="",
    var name: String? ="",
    var price: String? ="",
    var imageUrl: String? ="",
    var coffeeSize:String="") : Serializable