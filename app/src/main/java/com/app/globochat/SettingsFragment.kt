package com.app.globochat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.*


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings,rootKey)

        val dataStore = DataStore()
//        preferenceManager.preferenceDataStore = dataStore

        val accSettings = findPreference<Preference>(getString(R.string.key_account_settings))

        accSettings?.setOnPreferenceClickListener {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController = navHostFragment.navController
            val action = SettingsFragmentDirections.actionSettingsToAccSettings()
            navController.navigate(action)

            true
        }

        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(context)
        preferenceManager.getString(getString(R.string.key_auto_reply_time),"")

        val autoDownload = preferenceManager.getBoolean(getString(R.string.key_auto_download),false)

        val status = findPreference<Preference>(getString(R.string.key_status))

        status?.setOnPreferenceChangeListener { preference, newValue ->
            val newVal = newValue as String

            if(newVal.contains("bad")){
                Toast.makeText(context,"What the hell is that????",Toast.LENGTH_LONG).show()
                false
            }else
                true
        }
        val notificationPref = findPreference<SwitchPreferenceCompat>(getString(R.string.key_new_msg_notif))
        notificationPref?.summaryProvider = Preference.SummaryProvider<SwitchPreferenceCompat> { switchPref ->

            if(switchPref?.isChecked!!)
                 "On"
            else
                "Off"

        }
        notificationPref?.preferenceDataStore = dataStore
//        dataStore.getBoolean()


    }
    class DataStore : PreferenceDataStore() {

        override fun getBoolean(key: String?, defValue: Boolean): Boolean {
            return defValue
        }

        override fun putBoolean(key: String?, value: Boolean) {
            if(key == "key_new_msg_notif")
            {

            }

        }
    }


}