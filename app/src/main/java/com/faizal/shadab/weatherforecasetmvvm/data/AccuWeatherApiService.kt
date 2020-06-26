package com.faizal.shadab.weatherforecasetmvvm.data

import com.faizal.shadab.weatherforecasetmvvm.data.db.network.accuweather.AccuWeatherLocationResponse
import com.faizal.shadab.weatherforecasetmvvm.data.db.network.accuweather.AccuWeatherResponse
import com.faizal.shadab.weatherforecasetmvvm.data.db.network.ConnectivityInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//http://dataservice.accuweather.com/forecasts/v1/daily/5day/202396?apikey=Z1Ab1nWKqTkmrNjjJW5W2XGTzsKrZwH3&metric=true
//http://dataservice.accuweather.com/locations/v1/cities/search?apikey=Z1Ab1nWKqTkmrNjjJW5W2XGTzsKrZwH3&q=delhi

const val BASE_URL = "http://dataservice.accuweather.com/"
const val ACCU_WEATHER_API_KEY = "Z1Ab1nWKqTkmrNjjJW5W2XGTzsKrZwH3"

interface AccuWeatherApiService {
    @GET("forecasts/v1/daily/5day/{location_id}")
    fun getForecast(
        @Path("location_id") location_id: String = "202396",
        @Query("metric") isMetric: String = "true"
    ): Deferred<AccuWeatherResponse>

    @GET("locations/v1/cities/search")
    fun getLocationFromApi(
        @Query("q") location: String = "delhi"
    ): Deferred<AccuWeatherLocationResponse>
    companion object{
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): AccuWeatherApiService{
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("apikey", ACCU_WEATHER_API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AccuWeatherApiService::class.java)
        }
    }
}