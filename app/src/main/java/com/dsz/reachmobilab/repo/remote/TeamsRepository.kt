package com.dsz.reachmobilab.repo.remote

import androidx.lifecycle.LiveData
import com.dsz.reachmobilab.domain.Countries
import com.dsz.reachmobilab.domain.Teams

interface TeamsRepository {

    suspend fun searchTeams(s: String): Teams

    suspend fun getTeamsByLeagueId(id: String): Teams

}