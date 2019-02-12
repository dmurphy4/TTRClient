package com.d.tickettoride.util

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.d.tickettoride.R
import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.presenters.ChooseGamePresenter
import com.d.tickettoride.presenters.IChooseGamePresenter
import kotlinx.android.synthetic.main.row_game_list.view.*

class GameInfoAdapter(private val gameList: ArrayList<GameInfo>,
                      private val chooseGamePresenter: IChooseGamePresenter,
                      var selectedRowIndex: Int = -1) :
      RecyclerView.Adapter<GameInfoAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = parent.inflate(R.layout.row_game_list, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = gameList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val gameInfo = gameList[position]
        holder.bind(gameInfo)
        holder.view.setOnClickListener {
            selectedRowIndex = position
            chooseGamePresenter.setSelectedGameInfo(gameList[position])
            notifyDataSetChanged()
        }

        if (selectedRowIndex == position) {
            holder.view.row_game_name_layout.setBackgroundColor(Color.parseColor("#BEEB9F"))
            holder.view.row_game_name.setTextColor(Color.parseColor("#000000"))
        } else {
            holder.view.row_game_name_layout.setBackgroundColor(Color.parseColor("#79BD8F"))
            holder.view.row_game_name.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(gameInfo: GameInfo) {
            view.row_game_name.text = gameInfo.gameName
            view.row_num_players.text = gameInfo.numPlayers.toString()
        }
    }

}