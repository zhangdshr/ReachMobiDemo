package com.dsz.reachmobilab.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsz.reachmobilab.R
import com.dsz.reachmobilab.adapter.TeamListAdapter
import com.dsz.reachmobilab.db.model.Leagues
import com.dsz.reachmobilab.domain.Team
import com.dsz.reachmobilab.ui.team.TeamDetailActivity
import com.dsz.reachmobilab.utils.CommonUtil
import com.dsz.reachmobilab.utils.MyDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.main_fragment.*

class NotificationsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var notificationsViewModel: NotificationsViewModel

    private lateinit var leaguesNameArray: Array<String>

    private lateinit var leaguesList: List<Leagues>

    private lateinit var teamListAdapter: TeamListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        notificationsViewModel.teams.observe(viewLifecycleOwner, Observer {
            println("debug teams $it")

            if (it.teams.isNullOrEmpty()) {
                teamListAdapter.clearList()
            } else {
                it.teams.let { teams -> teamListAdapter.submitList(teams) }
            }

            teamListAdapter.notifyDataSetChanged()
        })

        notificationsViewModel.leagues.observe(viewLifecycleOwner, Observer {
            leaguesList = it
            leaguesNameArray = CommonUtil.convertLeaguesToArrayString(it)
            initSpinner()
        })

        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        notificationsViewModel.getLeagues()
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
                println("debug long click " + position + " item")
            }
        })
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

        spinner_leagues.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        notificationsViewModel.getTeamsByLeagueId(leaguesList[position].leagueId)
    }
}