package com.dsz.reachmobilab.repo.remote

import com.dsz.reachmobilab.domain.Events
import com.dsz.reachmobilab.request.RetrofitService

object EventsRepositoryImpl : EventsRepository {
    override suspend fun getEventsByLeagueId(id: String): Events {
        return RetrofitService.eventsApi.getEventsByLeagueId(id)
    }
}