package com.dsz.reachmobilab.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsz.reachmobilab.domain.Teams
import com.dsz.reachmobilab.repo.remote.TeamsRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel(), IMainViewModel {

    private val _teams: MutableLiveData<Teams> = MutableLiveData()

    val teams: LiveData<Teams> get() = _teams

    override fun searchTeams(s: String) {
        viewModelScope.launch {
            withContext(IO) {
                _teams.postValue(TeamsRepositoryImpl.searchTeams(s))
            }
        }
    }

}