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
import com.d.tickettoride.presenters.IChooseGamePresenter
import com.d.tickettoride.util.GameInfoAdapter
import com.d.tickettoride.util.afterTextChanged
import kotlinx.android.synthetic.main.activity_choose_game.*

class ChooseGameActivity : AppCompatActivity(), IChooseGameView {

    private lateinit var adapter: GameInfoAdapter
    private val chooseGamePresenter: IChooseGamePresenter = ChooseGamePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_game)

        number_picker.minValue = 2
        number_picker.maxValue = 5

        adapter = GameInfoAdapter(chooseGamePresenter.getAvailableGamesList(), this)
        available_games_list.layoutManager = LinearLayoutManager(this) // Displays games 1 per row
        available_games_list.adapter = adapter
        available_games_list.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        // Disable buttons to start with
        button_create_game.isEnabled = false
        button_join_game.isEnabled = false

        game_name.afterTextChanged { gameName ->
            button_create_game.isEnabled = gameName.isNotEmpty()
        }
        button_create_game.setOnClickListener {
            chooseGamePresenter.createNewGame(GameInfo(game_name.text.toString(), number_picker.value))
        }
        button_join_game.setOnClickListener {
            val gameList = chooseGamePresenter.getAvailableGamesList()
            chooseGamePresenter.joinExistingGame(gameList[adapter.selectedRowIndex])
        }

        chooseGamePresenter.startPoller() // make sure we take care of this for logging out (if we log out)
    }

    override fun startLobbyActivity() {
        startActivity(Intent(this, LobbyActivity::class.java))
    }

    override fun displayGameInList() {
        val gameList = chooseGamePresenter.getAvailableGamesList()
        adapter.notifyItemInserted(gameList.size)
    }

    override fun removeGameFromList(gameInfo: GameInfo?) {
        val gameList = chooseGamePresenter.getAvailableGamesList()
        adapter.notifyItemRemoved(gameList.indexOf(gameInfo))
    }

    override fun enableJoinGameButton(enable: Boolean) {
        button_join_game.isEnabled = enable
    }
}
