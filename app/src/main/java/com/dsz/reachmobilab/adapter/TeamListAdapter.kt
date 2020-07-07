package com.dsz.reachmobilab.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dsz.reachmobilab.R
import com.dsz.reachmobilab.domain.Team
import com.dsz.reachmobilab.domain.Teams
import kotlinx.android.synthetic.main.layout_team_list_item.view.*

class TeamListAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<Team> = mutableListOf()

    fun submitList(teamList: List<Team>) {
        items = teamList as MutableList<Team>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TeamViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_team_list_item,
                parent,
                false
            ), context
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is TeamViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    class TeamViewHolder
    constructor(
        itemView: View, private val context: Context
    ) : RecyclerView.ViewHolder(itemView) {

        val team_name = itemView.teamName
        val team_country = itemView.teamCountry
        val badge_image = itemView.badgeImage

        fun bind(team: Team) {

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(team.strTeamBadge)
                .into(badge_image)
            team_name.setText(team.strTeam)
            team_country.setText(team.strCountry)
        }

    }

}