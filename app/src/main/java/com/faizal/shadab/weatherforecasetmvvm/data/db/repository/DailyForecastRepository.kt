package com.faizal.shadab.weatherforecasetmvvm.data.db.repository

import androidx.lifecycle.LiveData
import com.faizal.shadab.weatherforecasetmvvm.data.db.network.accuweather.AccuWeatherResponse

interface DailyForecastRepository {
    suspend fun getDailyForecast(): LiveData<AccuWeatherResponse>
}