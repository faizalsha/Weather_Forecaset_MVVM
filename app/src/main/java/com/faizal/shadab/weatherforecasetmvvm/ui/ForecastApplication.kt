package com.faizal.shadab.weatherforecasetmvvm.ui

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.faizal.shadab.weatherforecasetmvvm.R
import com.faizal.shadab.weatherforecasetmvvm.data.ApixuWeatherApiService
import com.faizal.shadab.weatherforecasetmvvm.data.db.ForecastDatabase
import com.faizal.shadab.weatherforecasetmvvm.data.db.network.ConnectivityInterceptor
import com.faizal.shadab.weatherforecasetmvvm.data.db.network.ConnectivityInterceptorImpl
import com.faizal.shadab.weatherforecasetmvvm.data.db.network.response.WeatherNetworkDataSource
import com.faizal.shadab.weatherforecasetmvvm.data.db.network.response.WeatherNetworkDataSourceImpl
import com.faizal.shadab.weatherforecasetmvvm.data.db.repository.ForecastRepository
import com.faizal.shadab.weatherforecasetmvvm.data.db.repository.ForecastRepositoryImpl
import com.faizal.shadab.weatherforecasetmvvm.data.provider.LocationProvider
import com.faizal.shadab.weatherforecasetmvvm.data.provider.LocationProviderImpl
import com.faizal.shadab.weatherforecasetmvvm.data.provider.UnitProvider
import com.faizal.shadab.weatherforecasetmvvm.data.provider.UnitProviderImpl
import com.faizal.shadab.weatherforecasetmvvm.ui.weather.current.CurrentWeatherViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class ForecastApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))
        bind<ForecastDatabase>() with singleton { ForecastDatabase(instance()) }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind<ApixuWeatherApiService>() with singleton { ApixuWeatherApiService(instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind<FusedLocationProviderClient>() with singleton { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl(instance(), instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance<ForecastDatabase>().currentWeatherDao(), instance<ForecastDatabase>().weatherLocationDao(), instance(), instance(), instance()) }
        bind<CurrentWeatherViewModelFactory>() with singleton { CurrentWeatherViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        PreferenceManager.setDefaultValues(this, R.xml.prefs, true)
    }

}