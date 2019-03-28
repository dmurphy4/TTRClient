package com.d.tickettoride.service

import com.d.tickettoride.model.GameCreator
import com.d.tickettoride.model.RootModel

class BeginPlayService {

    companion object {
        val instance = BeginPlayService()
    }

    fun startGame(game: GameCreator) {
        val model = RootModel.instance
        model.game = game.createGame()
        model.gameStarted = true
    }
}