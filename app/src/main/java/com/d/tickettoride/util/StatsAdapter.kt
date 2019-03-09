package com.d.tickettoride.util

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.d.tickettoride.R
import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.model.PlayerColor
import com.d.tickettoride.model.PlayerInfo
import com.d.tickettoride.views.iviews.IChooseGameView
import com.d.tickettoride.views.iviews.IStatsView
import kotlinx.android.synthetic.main.row_game_list.view.*
import kotlinx.android.synthetic.main.row_stats_list.view.*

class StatsAdapter(private val playerInfoList: ArrayList<PlayerInfo>,
                   private val StatsFragment: IStatsView,
                   var selectedRowIndex: Int = -1) :
    RecyclerView.Adapter<StatsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = parent.inflate(R.layout.row_stats_list, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = playerInfoList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val stats = playerInfoList[position]
        holder.bind(stats)
        holder.view.setOnClickListener {
            selectedRowIndex = position
            notifyDataSetChanged()
        }
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(stats: PlayerInfo) {
            view.name.text = stats.username
            view.score.text = stats.score.toString()
            view.numDestCards.text = stats.numDestCards.toString()
            view.numTrainCards.text = stats.numTrainCards.toString()
            view.numCars.text = stats.numTrains.toString()
            view.color.text = stats.color.toString()
            if (stats.yourTurn) {
                view.row_player_stats_layout.setBackgroundColor(Color.parseColor("#BEEB9F"))
            }
            else {
                view.row_player_stats_layout.setBackgroundColor(Color.parseColor("#79BD8F"))
            }
            view.name.setTextColor(Color.parseColor("#FFFFFF"))
            view.scoreLabel.setTextColor(Color.parseColor("#FFFFFF"))
            view.score.setTextColor(Color.parseColor("#FFFFFF"))
            view.trainCardsLabel.setTextColor(Color.parseColor("#FFFFFF"))
            view.numTrainCards.setTextColor(Color.parseColor("#FFFFFF"))
            view.destCardsLabel.setTextColor(Color.parseColor("#FFFFFF"))
            view.numDestCards.setTextColor(Color.parseColor("#FFFFFF"))
            view.trainCarsLabel.setTextColor(Color.parseColor("#FFFFFF"))
            view.numCars.setTextColor(Color.parseColor("#FFFFFF"))

        }

        private fun getColor(color: PlayerColor): Int {
            return when (color) {
                PlayerColor.RED -> Color.parseColor("#ff0000")
                PlayerColor.BLACK -> Color.parseColor("#000000")
                PlayerColor.BLUE -> Color.parseColor("#48A3CF")
                PlayerColor.GREEN -> Color.parseColor("#669900")
                PlayerColor.YELLOW -> Color.parseColor("#ffff00")
                PlayerColor.NONE -> Color.parseColor("#a6a6a6")
            }
        }

    }

}