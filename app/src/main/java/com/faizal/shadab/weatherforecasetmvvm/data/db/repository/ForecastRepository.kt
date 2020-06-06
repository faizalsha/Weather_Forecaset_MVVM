package com.faizal.shadab.weatherforecasetmvvm.data.db.repository

import androidx.lifecycle.LiveData
import com.faizal.shadab.weatherforecasetmvvm.data.db.entity.CurrentWeatherEntry
import com.faizal.shadab.weatherforecasetmvvm.data.db.entity.WeatherLocation

interface ForecastRepository {
    suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}