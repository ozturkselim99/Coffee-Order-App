package com.selim.basicexample

import android.os.Parcel
import android.os.Parcelable


data class Coffee(var name:String?, var image:Int, var price:String?, var size:String?, var cream:String?, var chocolateSyrup:String?, var sugar:String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(image)
        parcel.writeString(price)
        parcel.writeString(size)
        parcel.writeString(cream)
        parcel.writeString(chocolateSyrup)
        parcel.writeString(sugar)
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