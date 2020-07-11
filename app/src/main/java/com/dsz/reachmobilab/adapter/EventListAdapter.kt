package com.dsz.reachmobilab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsz.reachmobilab.R
import com.dsz.reachmobilab.domain.Event
import com.dsz.reachmobilab.domain.Team
import kotlinx.android.synthetic.main.layout_event_list_item.view.*

class EventListAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<Event> = mutableListOf()

    fun submitList(eventList: List<Event>) {
        items = eventList as MutableList<Event>
    }

    fun clearList() {
        items.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EventViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_event_list_item,
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

            is EventViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    class EventViewHolder
    constructor(
        itemView: View, private val context: Context
    ) : RecyclerView.ViewHolder(itemView) {

        val sport_textView = itemView.sport_textView
        val leagues_textView = itemView.leagues_textView
        val event_textView = itemView.event_textView
        val home_team_textView = itemView.home_team_textView
        val away_team_textView = itemView.away_team_textView
        val round_textView = itemView.round_textView
        val time_textView = itemView.time_textView
        val season_textView = itemView.season_textView

        fun bind(event: Event) {

            sport_textView.text = event.strSport
            leagues_textView.text = event.strLeague
            event_textView.text = event.strEvent
            home_team_textView.text = "Home: ${event.strHomeTeam}"
            away_team_textView.text = "Away: ${event.strAwayTeam}"
            round_textView.text = "Round: ${event.intRound}"
            time_textView.text = "Game time: ${event.strEvent} ${event.strTime}"
            season_textView.text = "Season: ${event.strSeason}"
        }

    }

}