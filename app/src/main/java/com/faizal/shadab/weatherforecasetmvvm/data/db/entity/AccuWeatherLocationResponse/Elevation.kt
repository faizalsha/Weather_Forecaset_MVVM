package com.faizal.shadab.weatherforecasetmvvm.data.db.entity.AccuWeatherLocationResponse


import com.google.gson.annotations.SerializedName

data class Elevation(
    @SerializedName("Imperial")
    val imperial: Imperial,
    @SerializedName("Metric")
    val metric: Metric
)