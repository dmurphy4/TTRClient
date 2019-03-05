package com.d.tickettoride.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d.tickettoride.R
import com.d.tickettoride.model.PlayerInfo
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.presenters.StatsPresenter
import com.d.tickettoride.presenters.ipresenters.IStatsPresenter
import com.d.tickettoride.util.StatsAdapter
import com.d.tickettoride.views.iviews.IStatsView
import kotlinx.android.synthetic.main.fragment_stats.*

class StatsFragment : Fragment(), IStatsView {
    private lateinit var adapter: StatsAdapter
    private val statsPresenter: IStatsPresenter = StatsPresenter(this)
    var rootModel: RootModel = RootModel.instance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        // convert Map into array of PlayerInfo for the Adapter
        //val playerInfo = ArrayList(rootModel.game!!.gamePlayers.values)

        view.post {
            val playerInfo = ArrayList<PlayerInfo>()

            adapter = StatsAdapter(playerInfo, this)
            player_info_list.layoutManager = LinearLayoutManager(activity) // Displays games 1 per row
            player_info_list.adapter = adapter
            player_info_list.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
        }

        return view
    }

    override fun updateStats() {
        adapter.notifyDataSetChanged()
    }
}