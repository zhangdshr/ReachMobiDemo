package com.dsz.reachmobilab.request

import com.dsz.reachmobilab.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val teamApi: TeamApi by lazy {
        retrofit.create(TeamApi::class.java)
    }

    val leaguesApi: LeaguesApi by lazy {
        retrofit.create(LeaguesApi::class.java)
    }

    val eventsApi: EventApi by lazy {
        retrofit.create(EventApi::class.java)
    }

}