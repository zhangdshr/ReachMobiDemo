package com.dsz.reachmobilab.repo.local

import com.dsz.reachmobilab.db.SportDatabase
import com.dsz.reachmobilab.db.model.Leagues
import org.kodein.di.*

class DBRepositoryDI(override val di: DI) : DIAware {

    private val db: SportDatabase by instance()

    suspend fun getLeagues(): List<Leagues> {
        return db.sportDao().getLeagues()
    }

    suspend fun insertLeague(leagues: Leagues) {
        db.sportDao().insertLeagues(leagues)
    }

}