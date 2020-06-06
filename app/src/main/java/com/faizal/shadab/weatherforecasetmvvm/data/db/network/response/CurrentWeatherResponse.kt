package com.faizal.shadab.weatherforecasetmvvm.data.db.network.response

import com.faizal.shadab.weatherforecasetmvvm.data.db.entity.CurrentWeatherEntry
import com.faizal.shadab.weatherforecasetmvvm.data.db.entity.WeatherLocation
import com.faizal.shadab.weatherforecasetmvvm.data.db.entity.Request
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: WeatherLocation,
    val request: Request
)