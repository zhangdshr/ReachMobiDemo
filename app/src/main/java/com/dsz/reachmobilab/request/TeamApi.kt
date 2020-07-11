package com.dsz.reachmobilab.request

import com.dsz.reachmobilab.domain.Teams
import com.dsz.reachmobilab.utils.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamApi {

    @GET("searchteams.php")
    suspend fun searchTeamsByName(
        @Query("t") s: String
    ): Teams

    @GET("lookup_all_teams.php")
    suspend fun getTeamsByLeagueId(
        @Query("id") id: String
    ): Teams

}