package com.selim.basicexample.model

import android.os.Parcel
import android.os.Parcelable

class Coffee(
    var name: String? ="",
    var price: String? ="",
    var imageUrl: String? =""
): Parcelable
{
    var count:Int=0
    var coffeeSize:String=""

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
        count = parcel.readInt()
        coffeeSize = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(imageUrl)
        parcel.writeInt(count)
        parcel.writeString(coffeeSize)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coffee> {
        override fun createFromParcel(parcel: Parcel): Coffee {
            return Coffee(parcel)
        }

        override fun newArray(size: Int): Array<Coffee?> {
            return arrayOfNulls(size)
        }
    }


}
