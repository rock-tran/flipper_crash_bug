package com.base.fragments.toolbox

import android.os.Parcel
import android.os.Parcelable

class NavigationState : Parcelable {
    val placeholder: Int
    val backStackName: String

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(placeholder)
        dest.writeString(backStackName)
    }

    constructor(parcel: Parcel) {
        placeholder = parcel.readInt()
        backStackName = parcel.readString()!!
    }

    @JvmOverloads
    constructor(type: Int, backStackName: String = (Integer.MAX_VALUE * Math.random()).toInt().toString()) {
        this.placeholder = type
        this.backStackName = backStackName
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<NavigationState> = object : Parcelable.Creator<NavigationState> {

            override fun newArray(size: Int): Array<NavigationState?> {
                return arrayOfNulls(size)
            }

            override fun createFromParcel(source: Parcel): NavigationState {
                return NavigationState(source)
            }
        }
    }
}
