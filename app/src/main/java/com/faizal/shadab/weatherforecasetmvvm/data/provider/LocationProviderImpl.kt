package com.faizal.shadab.weatherforecasetmvvm.data.provider

import com.faizal.shadab.weatherforecasetmvvm.data.db.entity.WeatherLocation

class LocationProviderImpl : LocationProvider {
    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        //todo manage this hard coded code
        return true
    }

    override suspend fun getPreferredLocationString(): String {
        //todo: change to general location
        return "Delhi"
    }
}