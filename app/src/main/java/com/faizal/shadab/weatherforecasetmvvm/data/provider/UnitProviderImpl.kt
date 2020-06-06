package com.faizal.shadab.weatherforecasetmvvm.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


const val UNIT_SYSTEM = "UNIT_SYSTEM"
class UnitProviderImpl(context: Context) : UnitProvider {
    private val appContext = context.applicationContext
    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)
    override fun getUnitSystem(): String {
        val unit =  preference.getString(UNIT_SYSTEM, "f")
        if(unit != null)
            return unit
        else
            return "f"
    }
}