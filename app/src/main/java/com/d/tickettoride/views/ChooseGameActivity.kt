package com.d.tickettoride.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.d.tickettoride.R
import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.model.RootModel
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

        adapter = GameInfoAdapter(RootModel.instance.gameList, chooseGamePresenter)

        // Set RecyclerView's layout manager - the linear layout manager
        // displays the list like normal
        available_games_list.layoutManager = LinearLayoutManager(this)
        available_games_list.adapter = adapter
        available_games_list.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        button_create_game.isEnabled = false

        game_name.afterTextChanged { x ->
            button_create_game.isEnabled = x.isNotEmpty()
        }

        button_create_game.setOnClickListener {
            chooseGamePresenter.createNewGame(GameInfo(game_name.text.toString(), number_picker.value))
        }
        button_join_game.setOnClickListener {
            chooseGamePresenter.joinExistingGame(RootModel.instance.gameList[adapter.selectedRowIndex])
        }

        chooseGamePresenter.startPoller() // make sure we take care of this for logging out (if we log out)
    }

    override fun startLobbyActivity() {
        startActivity(Intent(this, LobbyActivity::class.java))
    }

    override fun displayGameInList(gameInfo: GameInfo) {
        adapter.notifyItemInserted(RootModel.instance.gameList.size)
    }

    override fun removeGameFromList(gameInfo: GameInfo?) {
        adapter.notifyItemRemoved(RootModel.instance.gameList.indexOf(gameInfo))
    }
}
