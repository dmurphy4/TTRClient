package com.d.tickettoride.command.client

import com.d.tickettoride.model.Game
import com.d.tickettoride.service.BeginPlayService

class CBeginPlayCommand(private val game: Game) : ICommand {

    override fun execute() {
        BeginPlayService().setCurrentGame(game)
    }
}