package com.dsz.reachmobilab.ui.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.dsz.reachmobilab.db.model.Leagues
import com.dsz.reachmobilab.domain.Events
import com.dsz.reachmobilab.repo.local.DBRepository
import com.dsz.reachmobilab.repo.remote.EventsRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: DBRepository = DBRepository.getInstance(application)

    private val _events: MutableLiveData<Events> = MutableLiveData()

    val events: LiveData<Events> get() = _events

    fun getEventsByLeagueId(id: String) {
        viewModelScope.launch {
            CoroutineScope(IO).launch {
                _events.postValue(EventsRepositoryImpl.getEventsByLeagueId(id))
            }
        }
    }

    private var _leagues: MutableLiveData<List<Leagues>> = MutableLiveData()

    val leagues: LiveData<List<Leagues>> get() = _leagues

    fun getLeagues() {
        viewModelScope.launch {
            CoroutineScope(IO).launch {
                _leagues.postValue(repository.getLeagues())
            }
        }
    }

}