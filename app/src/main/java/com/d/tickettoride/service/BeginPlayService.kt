package com.d.tickettoride.service

import com.d.tickettoride.model.Game
import com.d.tickettoride.model.RootModel

class BeginPlayService {

    companion object {
        val instance = BeginPlayService()
    }

    fun setCurrentGame(game:Game) {

    }

    fun startGame(bool:Boolean) {
        if (bool) {
            RootModel.instance.gameStarted = true
        }
    }
}