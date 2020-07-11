package com.dsz.reachmobilab.request

import com.dsz.reachmobilab.domain.Countries
import com.dsz.reachmobilab.domain.Events
import retrofit2.http.GET
import retrofit2.http.Query

interface EventApi {

    @GET("eventsnextleague.php")
    suspend fun getEventsByLeagueId(
        @Query("id") id: String
    ): Events

}