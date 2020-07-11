package com.dsz.reachmobilab.ui.dashboard

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.dsz.reachmobilab.R
import com.dsz.reachmobilab.adapter.LeaguesGridAdapter
import com.dsz.reachmobilab.domain.Country
import com.dsz.reachmobilab.ui.common.LeagueDetailDialog
import com.dsz.reachmobilab.utils.MyDecoration
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.layout_league_detail.*

class DashboardFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var leaguesGridAdapter: LeaguesGridAdapter

    private lateinit var countries: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()

        initSpinner()

        dashboardViewModel.countries.observe(this) {
            it?.let {
                leaguesGridAdapter.submitList(it.countrys)
                leaguesGridAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initSpinner() {
        countries = resources.getStringArray(R.array.array_countries)

        ArrayAdapter.createFromResource(
            context!!,
            R.array.array_countries,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner_countries.adapter = adapter
        }

        spinner_countries.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        println("debug spinner position $id")
        dashboardViewModel.searchAllLeaguesByCountry(countries[id.toInt()])
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    private fun initRecyclerView() {
        leagues_gird.apply {
            layoutManager = GridLayoutManager(activity, 3)
            addItemDecoration(MyDecoration(25))
            leaguesGridAdapter = LeaguesGridAdapter(context.applicationContext as Application)
            adapter = leaguesGridAdapter
        }

        leaguesGridAdapter?.setOnItemClickListener(object : LeaguesGridAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int, country: Country) {

                val fm = activity?.supportFragmentManager
                val myLeagueDetailDialog = LeagueDetailDialog(country)

                fm?.let { myLeagueDetailDialog.show(it, "league_detail_dialog") }

            }

            override fun onItemLongClick(view: View, position: Int, country: Country) {
                println("long click " + position + " item")
            }
        })
    }
}