package es.kab.footballproject.Fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import es.kab.footballproject.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}