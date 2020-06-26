package com.faizal.shadab.weatherforecasetmvvm.data.db.entity.AccuWeatherLocationResponse


import com.google.gson.annotations.SerializedName

data class ParentCity(
    @SerializedName("EnglishName")
    val englishName: String,
    @SerializedName("Key")
    val key: String,
    @SerializedName("LocalizedName")
    val localizedName: String
)