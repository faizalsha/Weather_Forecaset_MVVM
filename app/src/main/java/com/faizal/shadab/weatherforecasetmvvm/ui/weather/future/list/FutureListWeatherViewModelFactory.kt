package com.faizal.shadab.weatherforecasetmvvm.ui.weather.future.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faizal.shadab.weatherforecasetmvvm.data.db.repository.DailyForecastRepository
import com.faizal.shadab.weatherforecasetmvvm.ui.weather.current.CurrentWeatherViewModel

class FutureListWeatherViewModelFactory(
    private val dailyForecastRepository: DailyForecastRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureListWeatherViewModel(dailyForecastRepository) as T
    }
}