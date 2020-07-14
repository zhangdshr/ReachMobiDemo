package com.dsz.reachmobilab.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsz.reachmobilab.domain.Countries
import com.dsz.reachmobilab.repo.remote.LeaguesRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardViewModel : ViewModel() {

    private val _countries: MutableLiveData<Countries> = MutableLiveData()

    val countries: LiveData<Countries> get() = _countries

    fun searchAllLeaguesByCountry(c: String) {
        viewModelScope.launch {
            withContext(IO) {
                _countries.postValue(LeaguesRepositoryImpl.searchAllLeaguesByCountry(c))
            }
        }
    }

}