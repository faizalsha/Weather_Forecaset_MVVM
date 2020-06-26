package com.faizal.shadab.weatherforecasetmvvm.data.db.entity.AccuWeatherResponse


import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("Maximum")
    val maximum: Maximum,
    @SerializedName("Minimum")
    val minimum: Minimum
)