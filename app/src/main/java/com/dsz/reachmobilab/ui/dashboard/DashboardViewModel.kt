package com.dsz.reachmobilab.ui.dashboard

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsz.reachmobilab.domain.Countries
import com.dsz.reachmobilab.repo.remote.LeaguesRepositoryImpl
import com.dsz.reachmobilab.utils.Constants
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

class DashboardViewModel : ViewModel() {

    private val _countries: MutableLiveData<Countries> = MutableLiveData()

    val countries: LiveData<Countries> get() = _countries

    fun searchAllLeaguesByCountry(c: String) {
        viewModelScope.launch {

            withContext(IO) {

                val job = withTimeoutOrNull(Constants.JOB_TIMEOUT) {
                    _countries.postValue(LeaguesRepositoryImpl.searchAllLeaguesByCountry(c))
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