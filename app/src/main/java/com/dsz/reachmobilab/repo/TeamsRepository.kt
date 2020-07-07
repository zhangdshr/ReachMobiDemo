package com.dsz.reachmobilab.repo

import androidx.lifecycle.LiveData
import com.dsz.reachmobilab.domain.Teams

interface TeamsRepository {

    suspend fun searchTeams(s: String): Teams

}