package com.faizal.shadab.weatherforecasetmvvm.data.provider

import com.faizal.shadab.weatherforecasetmvvm.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}