package com.dsz.reachmobilab.repo.remote

import com.dsz.reachmobilab.domain.Countries
import com.dsz.reachmobilab.repo.remote.LeaguesRepository
import com.dsz.reachmobilab.request.RetrofitService

object LeaguesRepositoryImpl :
    LeaguesRepository {
    override suspend fun searchAllLeaguesByCountry(c: String): Countries {
        return RetrofitService.leaguesApi.searchAllLeaguesByCountry(c)
    }
}