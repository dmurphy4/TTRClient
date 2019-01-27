package com.d.tickettoride.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.d.tickettoride.R
import com.d.tickettoride.model.GameInfo
import kotlinx.android.synthetic.main.activity_choose_game.*

class ChooseGameActivity : AppCompatActivity(), IChooseGameView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_game)

        number_picker.minValue = 2
        number_picker.maxValue = 5
    }

    override fun startLobbyActivity() {
        startActivity(Intent(this, LobbyActivity::class.java))
    }

    override fun displayGameInList(gameInfo: GameInfo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
