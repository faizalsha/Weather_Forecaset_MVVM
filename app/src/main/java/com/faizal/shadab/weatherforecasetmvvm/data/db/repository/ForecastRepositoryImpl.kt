package com.faizal.shadab.weatherforecasetmvvm.data.db.repository

import androidx.lifecycle.LiveData
import com.faizal.shadab.weatherforecasetmvvm.data.db.CurrentWeatherDao
import com.faizal.shadab.weatherforecasetmvvm.data.db.WeatherLocationDao
import com.faizal.shadab.weatherforecasetmvvm.data.db.entity.CurrentWeatherEntry
import com.faizal.shadab.weatherforecasetmvvm.data.db.entity.WeatherLocation
import com.faizal.shadab.weatherforecasetmvvm.data.db.network.response.CurrentWeatherResponse
import com.faizal.shadab.weatherforecasetmvvm.data.db.network.response.WeatherNetworkDataSource
import com.faizal.shadab.weatherforecasetmvvm.data.provider.UnitProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Time
import java.util.*


class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherLocationDao: WeatherLocationDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val unitProvider: UnitProvider
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

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO){
            return@withContext weatherLocationDao.getLocation()
        }
    }

    private fun persistFetchedCurrentWeather(currentWeatherResponse: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(currentWeatherResponse.currentWeatherEntry)
            weatherLocationDao.upsert(currentWeatherResponse.location)
        }
    }

    private suspend fun initWeatherData() {
        val lastWeatherLocation = weatherLocationDao.getLocation().value
        if (lastWeatherLocation == null){
            fetchCurrentWeather()
            return
        }
        if (isFetchCurrentNeeded(lastWeatherLocation.zonedDateTime) || isLocationChanged())
            fetchCurrentWeather()
    }

    private fun isLocationChanged(): Boolean {
        //todo : check whether location has changed or not
        return true
    }

    private suspend fun fetchCurrentWeather(){
        println("special debug: ${unitProvider.getUnitSystem()}")
        weatherNetworkDataSource.fetchCurrentWeather(
            //todo fetch data according to location from settings or current location
            "Delhi",
            Locale.getDefault().language,
            unitProvider.getUnitSystem()
        )
    }

    private fun isFetchCurrentNeeded(lastWeatherResponseFetchTime: Time): Boolean {
        //TODO: change it with time
        val thirtyMinAgoMillis = System.currentTimeMillis() - 60000 * 30
        val thirtyMinAgo = Time(thirtyMinAgoMillis)
        return thirtyMinAgo > lastWeatherResponseFetchTime
    }
}