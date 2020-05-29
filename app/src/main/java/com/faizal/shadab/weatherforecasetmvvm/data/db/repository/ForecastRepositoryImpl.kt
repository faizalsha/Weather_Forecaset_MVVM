package com.faizal.shadab.weatherforecasetmvvm.data.db.repository

import androidx.lifecycle.LiveData
import com.faizal.shadab.weatherforecasetmvvm.data.db.CurrentWeatherDao
import com.faizal.shadab.weatherforecasetmvvm.data.db.entity.CurrentWeatherEntry
import com.faizal.shadab.weatherforecasetmvvm.data.db.network.response.CurrentWeatherResponse
import com.faizal.shadab.weatherforecasetmvvm.data.db.network.response.WeatherNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import java.util.*


class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever{
            persistFetchedCurrentWeather(it)
        }
    }


    override suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext currentWeatherDao.getWeather()
        }
    }

    private fun persistFetchedCurrentWeather(currentWeatherResponse: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(currentWeatherResponse.currentWeatherEntry)
        }
    }

    private suspend fun initWeatherData() {
        if (isFetchCurrentNeeded())
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather(
            "London",
            Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(): Boolean {
        //TODO: change it with time
        return true
    }
}