package com.selim.basicexample.model

import java.io.Serializable

data class Address(
    var id:String = "",
    var userId: String = "",
    var addressName: String = "",
    var city: String = "",
    var state: String = "",
    var avenue: String = "",
    var neighborhood: String = "",
    var street: String = "",
    var buildingNumber: Int = 0,
    var number: Int = 0
):Serializable