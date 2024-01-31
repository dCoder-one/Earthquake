package com.example.earthquake

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeatureCollection (
    val metadata : String,
    val features : String,
    val bbox : String
) : Parcelable {

}