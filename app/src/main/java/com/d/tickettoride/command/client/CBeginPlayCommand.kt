package com.d.tickettoride.command.client

import com.d.tickettoride.model.Game
import com.d.tickettoride.service.BeginPlayService
import com.d.tickettoride.service.ChooseGameService

class CBeginPlayCommand(private val game: Game?) : ICommand {

    override fun execute() {
        BeginPlayService.instance.startGame(true)
        ChooseGameService.instance.removeGameFromList(game!!.gameInfo)
    }
}