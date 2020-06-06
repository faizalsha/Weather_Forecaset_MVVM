package com.faizal.shadab.weatherforecasetmvvm.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.faizal.shadab.weatherforecasetmvvm.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.prefs)
    }
}