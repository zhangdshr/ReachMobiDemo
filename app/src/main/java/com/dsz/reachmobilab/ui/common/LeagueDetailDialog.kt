package com.dsz.reachmobilab.ui.common

import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dsz.reachmobilab.R
import com.dsz.reachmobilab.domain.Country
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.layout_league_detail.*

class LeagueDetailDialog(private val country: Country) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_league_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        initView()
    }

    private fun initView() {

        val requestOptions = RequestOptions()

        activity?.let {
            Glide.with(it)
                .applyDefaultRequestOptions(requestOptions)
                .load(country.strBanner)
                .into(imageView)
        }

        desc_textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        desc_textView.text = country.strDescriptionEN
        fb_address_textView.text = country.strFacebook

        back_button.setOnClickListener {
            this.dismiss()
        }

    }

}