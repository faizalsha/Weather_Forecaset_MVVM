package com.faizal.shadab.weatherforecasetmvvm.data.db.entity.AccuWeatherLocationResponse


import com.google.gson.annotations.SerializedName

data class GeoPosition(
    @SerializedName("Elevation")
    val elevation: Elevation,
    @SerializedName("Latitude")
    val latitude: Double,
    @SerializedName("Longitude")
    val longitude: Double
)