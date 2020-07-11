package com.dsz.reachmobilab.ui.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsz.reachmobilab.R
import com.dsz.reachmobilab.adapter.TeamListAdapter
import com.dsz.reachmobilab.domain.Team
import com.dsz.reachmobilab.ui.team.TeamDetailActivity
import com.dsz.reachmobilab.utils.MyDecoration
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    lateinit var teamListAdapter: TeamListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.teams.observe(this) {

            if (it.teams.isNullOrEmpty()) {
                teamListAdapter.clearList()
            } else {
                it.teams.let { teams -> teamListAdapter.submitList(teams) }
            }

            teamListAdapter.notifyDataSetChanged()

        }

        initRecyclerView()

        search_teams.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                println("debug query $query")
                viewModel.searchTeams(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

    private fun initRecyclerView() {
        team_list.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(MyDecoration(25))
            teamListAdapter = TeamListAdapter(context)
            adapter = teamListAdapter
        }

        teamListAdapter?.setOnItemClickListener(object : TeamListAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int, team: Team) {
                println("debug click $team ")

                val goTeamDetailIntent = Intent(activity, TeamDetailActivity::class.java).apply {
                    putExtra("team", team)
                }

                startActivity(goTeamDetailIntent)
            }

            override fun onItemLongClick(view: View, position: Int, team: Team) {
                println("long click " + position + " item")
            }
        })
    }

}