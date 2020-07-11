package com.dsz.reachmobilab.repo.remote

import com.dsz.reachmobilab.domain.Countries

interface LeaguesRepository {

    suspend fun searchAllLeaguesByCountry(c: String): Countries

}