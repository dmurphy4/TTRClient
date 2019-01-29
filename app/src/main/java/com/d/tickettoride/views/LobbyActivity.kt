package com.d.tickettoride.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.d.tickettoride.R

class LobbyActivity : AppCompatActivity(), ILobbyView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby)
    }

    override fun startGame() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setGameName(name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
