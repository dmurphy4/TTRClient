package com.d.tickettoride.command.client

import com.d.tickettoride.model.Game
import com.d.tickettoride.service.BeginPlayService
import com.d.tickettoride.service.CreateGameService

class CBeginPlayCommand(private val game: Game?, private val errorMessage:String?, private val success:Boolean) : ICommand {

    override fun execute() {
        //BeginPlayService.instance.setCurrentGame(game)
        BeginPlayService.instance.startGame(success)
        CreateGameService.instance.removeGameFromList(game!!.info)
    }
}