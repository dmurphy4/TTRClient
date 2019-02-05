package com.d.tickettoride.command.client

import com.d.tickettoride.model.Game
import com.d.tickettoride.service.BeginPlayService
import com.d.tickettoride.service.CreateGameService

class CBeginPlayCommand(private val game: Game) : ICommand {

    override fun execute() {
        BeginPlayService().setCurrentGame(game)
        CreateGameService().removeGameFromList(game.info!!)
    }
}