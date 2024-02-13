package es.kab.footballproject.Fragments

import android.graphics.Color
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import es.kab.footballproject.Activities.MainActivity
import es.kab.footballproject.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val colorPreference = findPreference<ListPreference>("background_color")
        colorPreference?.setOnPreferenceChangeListener { preference, newValue ->
            true
        }

    }
}