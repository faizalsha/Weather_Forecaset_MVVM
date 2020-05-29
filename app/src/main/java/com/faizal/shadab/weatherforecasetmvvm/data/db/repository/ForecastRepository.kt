package com.faizal.shadab.weatherforecasetmvvm.data.db.repository

import androidx.lifecycle.LiveData
import com.faizal.shadab.weatherforecasetmvvm.data.db.entity.CurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry>
}