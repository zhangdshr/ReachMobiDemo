package com.dsz.reachmobilab.repo

import androidx.lifecycle.LiveData
import com.dsz.reachmobilab.domain.Teams
import com.dsz.reachmobilab.request.RetrofitService
import com.dsz.reachmobilab.request.TeamApi

object TeamsRepositoryImpl : TeamsRepository {
    override suspend fun searchTeams(s: String): Teams {
        return RetrofitService.teamApi.searchTeamsByName(s)
    }
}