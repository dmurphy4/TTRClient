package com.d.tickettoride.util

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.d.tickettoride.R
import com.d.tickettoride.model.GameInfo
import kotlinx.android.synthetic.main.row_game_list.view.*

class GameInfoAdapter(private val gameList: ArrayList<GameInfo>) : RecyclerView.Adapter<GameInfoAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflatedView = parent.inflate(R.layout.row_game_list, false)
        return MyViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = gameList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val gameInfo = gameList[position]
        holder.bind(gameInfo)
    }

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view : View = v
        private var gameInfo : GameInfo? = null

        init {
            v.setOnClickListener(this)
        }

        fun bind(gameInfo: GameInfo) {
            this.gameInfo = gameInfo
            view.row_game_name.text = gameInfo.name
            view.row_num_players.text = gameInfo.numPlayers.toString()
        }

        override fun onClick(v: View) {
            Toast.makeText(itemView.context, "Clicked ${gameInfo?.name}, ${gameInfo?.numPlayers}",
                           Toast.LENGTH_LONG).show()
        }
    }

}