package com.faizal.shadab.weatherforecasetmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import com.faizal.shadab.weatherforecasetmvvm.data.db.repository.ForecastRepository
import com.faizal.shadab.weatherforecasetmvvm.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather()
    }
}
