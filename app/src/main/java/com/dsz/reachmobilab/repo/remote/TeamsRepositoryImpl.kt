package com.dsz.reachmobilab.repo.remote

import com.dsz.reachmobilab.domain.Teams
import com.dsz.reachmobilab.repo.remote.TeamsRepository
import com.dsz.reachmobilab.request.RetrofitService

object TeamsRepositoryImpl : TeamsRepository {
    override suspend fun searchTeams(s: String): Teams {
        return RetrofitService.teamApi.searchTeamsByName(s)
    }

    override suspend fun getTeamsByLeagueId(id: String): Teams {
        return RetrofitService.teamApi.getTeamsByLeagueId(id)
    }
}