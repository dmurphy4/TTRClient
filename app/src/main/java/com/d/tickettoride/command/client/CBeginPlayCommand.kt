package com.d.tickettoride.command.client

import com.d.tickettoride.model.Game
import com.d.tickettoride.service.BeginPlayService
import com.d.tickettoride.service.ChooseGameService
import com.d.tickettoride.service.ErrorMessageService

class CBeginPlayCommand(private val game: Game?, private val errorMessage:String?) : ICommand {

    override fun execute() {
        //BeginPlayService.instance.setCurrentGame(game)
        if (errorMessage == null) {
            BeginPlayService.instance.startGame(true)
            ChooseGameService.instance.removeGameFromList(game!!.info)
        }
        else {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
    }
}