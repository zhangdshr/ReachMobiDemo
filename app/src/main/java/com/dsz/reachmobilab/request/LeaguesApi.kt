package com.dsz.reachmobilab.request

import com.dsz.reachmobilab.domain.Countries
import retrofit2.http.GET
import retrofit2.http.Query

interface LeaguesApi {

    @GET("search_all_leagues.php")
    suspend fun searchAllLeaguesByCountry(
        @Query("c") c: String
    ): Countries

}