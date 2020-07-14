package com.dsz.reachmobilab.ui.team

import android.os.Bundle
import android.os.CountDownTimer
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dsz.reachmobilab.R
import com.dsz.reachmobilab.domain.Team
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.layout_league_detail.adView
import java.util.*

class TeamDetailActivity : AppCompatActivity() {

    private lateinit var team: Team

    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        team = intent.getSerializableExtra("team") as Team

        supportActionBar!!.setTitle(team.strTeam)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val requestOptions = RequestOptions()

        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(team.strTeamFanart1)
            .into(fanart_imageView)

        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(team.strTeamBanner)
            .into(banner_imageView)

        desc_team_textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        league_textView.text = team.strLeague
        desc_team_textView.text = team.strDescriptionEN

    }

}