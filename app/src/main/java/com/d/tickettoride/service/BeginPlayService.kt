package com.d.tickettoride.service

import com.d.tickettoride.model.GameCreator
import com.d.tickettoride.model.RootModel

class BeginPlayService {

    companion object {
        val instance = BeginPlayService()
    }

    fun startGame(game: GameCreator) {
        val model = RootModel.instance
        model.gameStarted = true
        model.game = game.createGame()

        //we need to figure out how to get the user its player info... or do we just leave it in the stats? idk
        //model.user!!.playerInfo = game.gamePlayers[model.user!!.username]
    }
}