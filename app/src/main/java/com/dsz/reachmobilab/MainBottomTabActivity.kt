package com.dsz.reachmobilab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.dsz.reachmobilab.db.model.Leagues
import com.dsz.reachmobilab.repo.local.DBRepository
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.main_bottom_tab_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainBottomTabActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_bottom_tab_activity)
        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        nav_view.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController)

        MobileAds.initialize(this) {}

        CoroutineScope(Dispatchers.IO).launch {
            DBRepository.getInstance(application).insertLeague(Leagues("4372", "BTCC"))
            DBRepository.getInstance(application).insertLeague(Leagues("4415", "English Rugby League Super League"))
            DBRepository.getInstance(application).insertLeague(Leagues("4391", "NFL"))
            DBRepository.getInstance(application).insertLeague(Leagues("4400", "Spanish Adelante"))
            DBRepository.getInstance(application).insertLeague(Leagues("4394", "Italian Serie B"))
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

}