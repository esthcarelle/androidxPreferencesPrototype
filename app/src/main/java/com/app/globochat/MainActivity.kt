package com.app.globochat

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.PreferenceManager
import com.app.globochat.R


class MainActivity : AppCompatActivity() ,SharedPreferences.OnSharedPreferenceChangeListener{

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // Get NavHost and NavController
        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController = navHostFrag.navController
//
//        // Get AppBarConfiguration
        appBarConfiguration = AppBarConfiguration(navController.graph)
//
//        // Link ActionBar with NavController
        setupActionBarWithNavController(navController, appBarConfiguration)
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(this)
        preferenceManager.getString(getString(R.string.key_auto_reply_time),"")



    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //this is executed after the value has already changed
    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {

    }

    override fun onResume() {
        super.onResume()
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this)
    }
}
