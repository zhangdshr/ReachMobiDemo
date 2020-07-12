package com.dsz.reachmobilab.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dsz.reachmobilab.R
import com.dsz.reachmobilab.db.model.Leagues
import com.dsz.reachmobilab.domain.Countries
import com.dsz.reachmobilab.domain.Country
import com.dsz.reachmobilab.domain.Team
import com.dsz.reachmobilab.repo.local.DBRepository
import kotlinx.android.synthetic.main.layout_leagues_grid_item.view.*
import kotlinx.android.synthetic.main.layout_team_list_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LeaguesGridAdapter(private val application: Application) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<Country> = mutableListOf()

    fun submitList(leaguesList: List<Country>) {
        items = leaguesList as MutableList<Country>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LeaguesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_leagues_grid_item,
                parent,
                false
            ), application
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is LeaguesViewHolder -> {
                holder.bind(items.get(position))

                holder.itemView.setOnClickListener {
                    onItemClickListener?.onItemClick(holder.itemView, position, items.get(position))
                }

                holder.itemView.setOnLongClickListener {
                    onItemClickListener?.onItemLongClick(
                        holder.itemView,
                        position,
                        items.get(position)
                    )
                    true
                }

            }

        }
    }

    class LeaguesViewHolder
    constructor(
        itemView: View, private val application: Application
    ) : RecyclerView.ViewHolder(itemView) {
        var leaguesName = itemView.leagues_name
        val leaguesButton = itemView.leagues_button
        val leaguesImage = itemView.leagues_image

        fun bind(country: Country) {

            leaguesName.text = country.strLeague

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(country.strBadge)
                .into(leaguesImage)

            leaguesButton.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch {

                    val len = DBRepository.getInstance(application).getLeagues().size

                    if (len >= 8) {
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(
                                application as Context,
                                "You can subscribe at most 8 leagues, paid users can lift the limitation.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        DBRepository.getInstance(application)
                            .insertLeague(Leagues(country.idLeague, country.strLeague))
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(
                                application as Context,
                                "You subscribe ${country.strLeague} successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }

            }

        }

    }

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int, country: Country)
        fun onItemLongClick(view: View, position: Int, country: Country)
    }

}