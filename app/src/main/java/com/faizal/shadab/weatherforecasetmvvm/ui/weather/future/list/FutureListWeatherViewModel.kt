package com.faizal.shadab.weatherforecasetmvvm.ui.weather.future.list

import androidx.lifecycle.ViewModel
import com.faizal.shadab.weatherforecasetmvvm.data.db.repository.DailyForecastRepository
import com.faizal.shadab.weatherforecasetmvvm.internal.lazyDeferred

class FutureListWeatherViewModel(
    private val dailyForecastRepository: DailyForecastRepository
) : ViewModel() {
    val forecastWeather by lazyDeferred {
        dailyForecastRepository.getDailyForecast()
    }
}
