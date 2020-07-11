package com.dsz.reachmobilab.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsz.reachmobilab.R
import com.dsz.reachmobilab.adapter.EventListAdapter
import com.dsz.reachmobilab.db.model.Leagues
import com.dsz.reachmobilab.domain.Event
import com.dsz.reachmobilab.utils.CommonUtil
import com.dsz.reachmobilab.utils.MyDecoration
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.adView
import kotlinx.android.synthetic.main.layout_league_detail.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    lateinit var eventListAdapter: EventListAdapter

    private lateinit var leaguesNameArray: Array<String>

    private lateinit var leaguesList: List<Leagues>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("debug home fragment onCreateView")

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("debug home fragment onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        println("debug home fragment onActivityCreated")

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        initRecyclerView()

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        homeViewModel.events.observe(viewLifecycleOwner, Observer {
            println("debug $it")
            eventListAdapter.clearList()
            it.events?.let {
                eventListAdapter.submitList(it)
            }
            eventListAdapter.notifyDataSetChanged()
        })

        homeViewModel.leagues.observe(viewLifecycleOwner, Observer {
            leaguesList = it
            leaguesNameArray = CommonUtil.convertLeaguesToArrayString(it)
            initSpinner()

        })

        homeViewModel.getLeagues()

    }

    private class MyOnItemSelectedListener(
        private var homeViewModel: HomeViewModel,
        private var leaguesList: List<Leagues>
    ) : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            homeViewModel.getEventsByLeagueId(leaguesList[position].leagueId)
        }

    }

    private fun initSpinner() {

        ArrayAdapter(
            context!!,
            android.R.layout.simple_spinner_item,
            leaguesNameArray
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner_leagues.adapter = adapter
        }

        spinner_leagues.onItemSelectedListener =
            MyOnItemSelectedListener(homeViewModel, leaguesList)
    }

    private fun initRecyclerView() {
        event_list.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(MyDecoration(25))
            eventListAdapter = EventListAdapter(context)
            adapter = eventListAdapter
        }
    }
}

