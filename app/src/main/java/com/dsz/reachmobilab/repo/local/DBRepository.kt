package com.dsz.reachmobilab.repo.local

import android.app.Application
import com.dsz.reachmobilab.db.SportDao
import com.dsz.reachmobilab.db.SportDatabase
import com.dsz.reachmobilab.db.model.Leagues

class DBRepository(application: Application) {

    companion object {
        @Volatile
        var instance: DBRepository? = null

        fun getInstance(application: Application): DBRepository {
            if (instance == null) {
                synchronized(DBRepository::class) {
                    if (instance == null) {
                        instance = DBRepository(application)
                    }
                }
            }
            return instance!!
        }
    }

    private var sportDao: SportDao

    init {
        val database: SportDatabase = SportDatabase.getInstance(application)!!
        sportDao = database.sportDao()
    }

    suspend fun getLeagues(): List<Leagues> {
        return sportDao.getLeagues()
    }

    suspend fun insertLeague(leagues: Leagues) {
        sportDao.insertLeagues(leagues)
    }

}