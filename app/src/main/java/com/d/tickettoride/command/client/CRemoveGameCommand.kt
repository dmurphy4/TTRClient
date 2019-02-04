package com.d.tickettoride.command.client

import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.service.CreateGameService

class CRemoveGameCommand(private val gameInfo: GameInfo) : ICommand {

    override fun execute() {
        CreateGameService().removeGameFromList(gameInfo)
    }
}