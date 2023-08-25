package com.example.movie.model

import android.os.Parcel
import android.os.Parcelable


data class Movie(
    val popularity: Double,
    val reviews: Int,
    val isVideo: Boolean,
    val poster: String?,
    val id: Int,
    val isAdult: Boolean,
    val background: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val duration: Int,
    val genreIds: List<Int>?,
    val title: String,
    val actors: List<Int>?,
    val rating: Double,
    val storyline: String,
    val releaseDate: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.createIntArray()?.toList(),
        parcel.readString() ?: "",
        parcel.createIntArray()?.toList(),
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(popularity)
        parcel.writeInt(reviews)
        parcel.writeByte(if (isVideo) 1 else 0)
        parcel.writeString(poster)
        parcel.writeInt(id)
        parcel.writeByte(if (isAdult) 1 else 0)
        parcel.writeString(background)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeInt(duration)
        parcel.writeIntArray(genreIds?.toIntArray())
        parcel.writeString(title)
        parcel.writeIntArray(actors?.toIntArray())
        parcel.writeDouble(rating)
        parcel.writeString(storyline)
        parcel.writeString(releaseDate)
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}

