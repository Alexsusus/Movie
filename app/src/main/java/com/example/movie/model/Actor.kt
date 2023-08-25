package com.example.movie.model

import android.os.Parcel
import android.os.Parcelable

data class Actor(
    val gender: Int,
    val id: Int,
    val name: String?,
    val original_name: String?,
    val profile_photo: String?,
    ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(gender)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(original_name)
        parcel.writeString(profile_photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Actor> {
        override fun createFromParcel(parcel: Parcel): Actor {
            return Actor(parcel)
        }

        override fun newArray(size: Int): Array<Actor?> {
            return arrayOfNulls(size)
        }
    }
}