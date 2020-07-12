package com.dsz.reachmobilab

import android.app.Application
import com.dsz.reachmobilab.db.SportDatabase
import com.dsz.reachmobilab.db.model.Leagues
import com.dsz.reachmobilab.di.instanceOfDB
import com.dsz.reachmobilab.repo.local.DBRepository
import com.dsz.reachmobilab.repo.local.DBRepositoryDI
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bind
import org.kodein.di.singleton

class MyApplication : Application(), DIAware {

    override val di by DI.lazy {
        /* bindings */

        import(androidXModule(this@MyApplication))

        bind<SportDatabase>() with singleton {
            instanceOfDB(applicationContext)
        }

    }

    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this) {}

        CoroutineScope(Dispatchers.IO).launch {
            DBRepository.getInstance(this@MyApplication).insertLeague(Leagues("4372", "BTCC"))
            DBRepository.getInstance(this@MyApplication).insertLeague(Leagues("4415", "English Rugby League Super League"))
            DBRepository.getInstance(this@MyApplication).insertLeague(Leagues("4391", "NFL"))
            DBRepository.getInstance(this@MyApplication).insertLeague(Leagues("4400", "Spanish Adelante"))
            DBRepository.getInstance(this@MyApplication).insertLeague(Leagues("4394", "Italian Serie B"))
//            DBRepositoryDI(di).getLeagues() this line just for show the kodein DI
        }

    }

}