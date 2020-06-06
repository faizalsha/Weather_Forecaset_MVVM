package com.faizal.shadab.weatherforecasetmvvm.data.db.network.response

import androidx.lifecycle.LiveData

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(location: String, languageCode: String, unit: String)
}