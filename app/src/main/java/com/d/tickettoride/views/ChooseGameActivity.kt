package com.d.tickettoride.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.d.tickettoride.R
import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.presenters.ChooseGamePresenter
import com.d.tickettoride.util.GameInfoAdapter
import kotlinx.android.synthetic.main.activity_choose_game.*

class ChooseGameActivity : AppCompatActivity(), IChooseGameView {

    private lateinit var adapter: GameInfoAdapter
    private var gameList = ArrayList<GameInfo>()
    private val chooseGamePresenter = ChooseGamePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_game)

        number_picker.minValue = 2
        number_picker.maxValue = 5

        adapter = GameInfoAdapter(gameList)

        // Set RecyclerView's layout manager - the linear layout manager
        // displays the list like normal
        available_games_list.layoutManager = LinearLayoutManager(this)
        available_games_list.adapter = adapter
        available_games_list.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        button_create_game.setOnClickListener {
            chooseGamePresenter.createNewGame(GameInfo(game_name.text.toString(), number_picker.value))
        }
    }

    override fun startLobbyActivity() {
        startActivity(Intent(this, LobbyActivity::class.java))
    }

    override fun displayGameInList(gameInfo: GameInfo) {
        gameList.add(gameInfo)
        adapter.notifyItemInserted(gameList.size)
    }
}
