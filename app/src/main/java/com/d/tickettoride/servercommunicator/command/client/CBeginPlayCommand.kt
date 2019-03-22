package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.model.GameCreator
import com.d.tickettoride.service.BeginPlayService
import com.d.tickettoride.service.ChooseGameService

class CBeginPlayCommand(private val game: GameCreator) : ICommand {
    override fun execute() {
        BeginPlayService.instance.startGame(game)
        ChooseGameService.instance.removeGameFromList(game.gameInfo)
    }
}