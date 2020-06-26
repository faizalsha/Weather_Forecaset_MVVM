package com.faizal.shadab.weatherforecasetmvvm.data.db.entity.AccuWeatherResponse


import com.google.gson.annotations.SerializedName


data class DailyForecast(
    @SerializedName("Date")
    val date: String,
    @SerializedName("Day")
    val day: Day,
    @SerializedName("EpochDate")
    val epochDate: Long,
    @SerializedName("Temperature")
    val temperature: Temperature
)
