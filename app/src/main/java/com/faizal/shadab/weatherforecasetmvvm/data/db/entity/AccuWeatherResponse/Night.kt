package com.faizal.shadab.weatherforecasetmvvm.data.db.entity.AccuWeatherResponse


import com.google.gson.annotations.SerializedName

data class Night(
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean,
    @SerializedName("Icon")
    val icon: Int,
    @SerializedName("IconPhrase")
    val iconPhrase: String
)