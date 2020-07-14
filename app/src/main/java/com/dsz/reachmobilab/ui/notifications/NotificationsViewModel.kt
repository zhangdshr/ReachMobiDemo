package com.dsz.reachmobilab.ui.notifications

import android.app.Application
import androidx.lifecycle.*
import com.dsz.reachmobilab.db.model.Leagues
import com.dsz.reachmobilab.domain.Teams
import com.dsz.reachmobilab.repo.local.DBRepository
import com.dsz.reachmobilab.repo.remote.TeamsRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationsViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: DBRepository = DBRepository.getInstance(application)

    private val _teams: MutableLiveData<Teams> = MutableLiveData()

    val teams: LiveData<Teams> get() = _teams

    fun getTeamsByLeagueId(id: String) {
        viewModelScope.launch {
            withContext(IO) {
                _teams.postValue(TeamsRepositoryImpl.getTeamsByLeagueId(id))
            }
        }
    }

    private var _leagues: MutableLiveData<List<Leagues>> = MutableLiveData()

    val leagues: LiveData<List<Leagues>> get() = _leagues

    fun getLeagues() {
        viewModelScope.launch {
            withContext(IO) {
                _leagues.postValue(repository.getLeagues())
            }
        }
    }

}