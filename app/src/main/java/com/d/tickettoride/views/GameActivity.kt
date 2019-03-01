package com.d.tickettoride.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.d.tickettoride.R
import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.presenters.GamePresenter
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.views.iviews.IGameView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity(), IGameView {

    private val gamePresenter: IGamePresenter = GamePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val inputStream = resources.openRawResource(R.raw.board)
        val boardString = inputStream.bufferedReader().use { it.readText() }
        val board = Gson().fromJson(boardString, Board::class.java)
        game_board.cities = board.cities
        game_board.setRouteData(board.routes)
        game_board.invalidate()

        game_board.onClaimRouteClicked = {
            Toast.makeText(this, "Route $it clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun enableMapClicks(enable: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun enableClaimButton(enable: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
