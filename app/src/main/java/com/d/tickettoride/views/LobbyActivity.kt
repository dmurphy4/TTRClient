package com.d.tickettoride.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.d.tickettoride.R
import com.d.tickettoride.presenters.LobbyPresenter
import com.d.tickettoride.presenters.ipresenters.ILobbyPresenter
import com.d.tickettoride.views.iviews.ILobbyView

class LobbyActivity : AppCompatActivity(), ILobbyView {

    private val lobbyPresenter: ILobbyPresenter = LobbyPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby)
    }

    override fun startGame() {

    }

    override fun setGameName(name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayErrorMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
