package com.dsz.reachmobilab.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.dsz.reachmobilab.db.model.Leagues
import com.dsz.reachmobilab.domain.Events
import com.dsz.reachmobilab.repo.local.DBRepository
import com.dsz.reachmobilab.repo.remote.EventsRepositoryImpl
import com.dsz.reachmobilab.utils.Constants
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _events: MutableLiveData<Events> = MutableLiveData()

    val events: LiveData<Events> get() = _events

    fun getEventsByLeagueId(id: String) {
        viewModelScope.launch {

            withContext(IO) {

                val job = withTimeoutOrNull(Constants.JOB_TIMEOUT) {
                    _events.postValue(EventsRepositoryImpl.getEventsByLeagueId(id))
                }

                if (job == null) {
                    println(
                        "Network request time longer than ${Constants.JOB_TIMEOUT} ms"
                    )
                }
            }

        }
    }

    private var repository: DBRepository = DBRepository.getInstance(application)

    private var _leagues: MutableLiveData<List<Leagues>> = MutableLiveData()

    val leagues: LiveData<List<Leagues>> get() = _leagues

    fun getLeagues() {
        viewModelScope.launch {

            withContext(IO) {

                val job = withTimeoutOrNull(Constants.JOB_TIMEOUT) {
                    _leagues.postValue(repository.getLeagues())
                }

                if (job == null) {
                    println(
                        "Network request time longer than ${Constants.JOB_TIMEOUT} ms"
                    )
                }
            }
        }
    }

}