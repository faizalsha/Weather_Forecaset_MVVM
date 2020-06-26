package com.faizal.shadab.weatherforecasetmvvm.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class ScopedFragment: Fragment(), CoroutineScope {
    lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }
    //there was a problem with onDestroy() with CurrentWeatherFragment (onDestroy was not getting called)
    override fun onDestroyView() {
        super.onDestroyView()
        job.cancel()
        println("debug: onDestroy Called")
    }
}
