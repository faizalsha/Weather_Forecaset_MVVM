package com.faizal.shadab.weatherforecasetmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faizal.shadab.weatherforecasetmvvm.data.db.entity.CURRENT_WEATHER_ID
import com.faizal.shadab.weatherforecasetmvvm.data.db.entity.CurrentWeatherEntry
import kotlinx.coroutines.selects.select

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeather() : LiveData<CurrentWeatherEntry>
}