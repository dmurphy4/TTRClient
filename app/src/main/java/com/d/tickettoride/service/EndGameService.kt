package com.d.tickettoride.service

import com.d.tickettoride.model.GameSummary
import com.d.tickettoride.model.RootModel

class EndGameService {

    companion object {
        val instance = EndGameService()
    }

    fun endGame(gameSummary: GameSummary, winner: String, winnerPoints: Int) {
        RootModel.instance.gameWinner = winner
        RootModel.instance.gameWinnerPoints = winnerPoints
        RootModel.instance.gameSummary = gameSummary
    }
}