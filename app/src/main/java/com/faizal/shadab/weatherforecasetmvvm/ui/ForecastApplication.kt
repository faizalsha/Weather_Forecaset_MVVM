package com.faizal.shadab.weatherforecasetmvvm.ui

import android.app.Application
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
import com.faizal.shadab.weatherforecasetmvvm.data.provider.UnitProvider
import com.faizal.shadab.weatherforecasetmvvm.data.provider.UnitProviderImpl
import com.faizal.shadab.weatherforecasetmvvm.ui.weather.current.CurrentWeatherViewModelFactory
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
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance<ForecastDatabase>().currentWeatherDao(), instance<ForecastDatabase>().weatherLocationDao(), instance(), instance()) }
        bind<CurrentWeatherViewModelFactory>() with singleton { CurrentWeatherViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        PreferenceManager.setDefaultValues(this, R.xml.prefs, true)
    }

}