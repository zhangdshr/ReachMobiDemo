package com.dsz.reachmobilab.ui.main

import android.content.Context
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
import com.dsz.reachmobilab.utils.MyDecoration
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
            println("debug: ${it.teams.size}")
            it?.let { it -> teamListAdapter.submitList(it.teams) }
            teamListAdapter.notifyDataSetChanged()
        }

        viewModel.searchTeams("Arsenal")

        initRecyclerView()

        search_teams.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchTeams(query.toString())
                Toast.makeText(context, query, Toast.LENGTH_SHORT).show()
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
    }

}