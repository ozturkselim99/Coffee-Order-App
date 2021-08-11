package com.selim.basicexample.model

data class Address(
    var userId: String = "",
    var addressName: String = "",
    var city: String = "",
    var district: String = "",
    var avenue: String = "",
    var neighborhood: String = "",
    var street: String = "",
    var buildingNumber: Int = 0,
    var number: Int = 0
)