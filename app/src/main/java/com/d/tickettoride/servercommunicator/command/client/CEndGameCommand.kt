package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.model.GameSummary
import com.d.tickettoride.service.EndGameService

class CEndGameCommand(val summary: GameSummary, val winner: String, val winnerPoints: Int) : ICommand {

    override fun execute() {
        println("$winner is the winner of the game! With ")
        EndGameService.instance.endGame(summary, winner, winnerPoints)
    }
}