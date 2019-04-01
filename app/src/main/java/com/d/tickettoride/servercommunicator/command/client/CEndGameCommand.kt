package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.model.GameSummary
import com.d.tickettoride.service.EndGameService

class CEndGameCommand(val gameSummary: GameSummary, val winner: String, val winnerPoints: Int) : ICommand {

    override fun execute() {
        EndGameService.instance.endGame(gameSummary, winner, winnerPoints)
    }
}