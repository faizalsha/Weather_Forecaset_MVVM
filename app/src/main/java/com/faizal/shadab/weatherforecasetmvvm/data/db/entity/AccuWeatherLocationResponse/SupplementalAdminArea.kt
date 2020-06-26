package com.faizal.shadab.weatherforecasetmvvm.data.db.entity.AccuWeatherLocationResponse


import com.google.gson.annotations.SerializedName

data class SupplementalAdminArea(
    @SerializedName("EnglishName")
    val englishName: String,
    @SerializedName("Level")
    val level: Int,
    @SerializedName("LocalizedName")
    val localizedName: String
)