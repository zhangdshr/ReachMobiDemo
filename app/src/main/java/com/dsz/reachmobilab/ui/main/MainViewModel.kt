package com.dsz.reachmobilab.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsz.reachmobilab.domain.Teams
import com.dsz.reachmobilab.repo.remote.TeamsRepositoryImpl
import com.dsz.reachmobilab.utils.Constants
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

class MainViewModel : ViewModel(), IMainViewModel {

    private val _teams: MutableLiveData<Teams> = MutableLiveData()

    val teams: LiveData<Teams> get() = _teams

    override fun searchTeams(s: String) {
        viewModelScope.launch {

            withContext(IO) {

                val job = withTimeoutOrNull(Constants.JOB_TIMEOUT) {
                    _teams.postValue(TeamsRepositoryImpl.searchTeams(s))
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