package com.d.tickettoride.service

import com.d.tickettoride.model.Game
import com.d.tickettoride.model.RootModel

class BeginPlayService {

    companion object {
        val instance = BeginPlayService()
    }

    fun setCurrentGame(game:Game) {

    }

    fun startGame(game:Game) {
        val model = RootModel.instance
        model.gameStarted = true
        model.game = game
        model.user!!.playerInfo = game.gamePlayers[model.user!!.username]
    }
}