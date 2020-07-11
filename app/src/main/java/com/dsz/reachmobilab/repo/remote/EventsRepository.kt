package com.dsz.reachmobilab.repo.remote

import com.dsz.reachmobilab.domain.Countries
import com.dsz.reachmobilab.domain.Events

interface EventsRepository {

    suspend fun getEventsByLeagueId(id: String): Events

}