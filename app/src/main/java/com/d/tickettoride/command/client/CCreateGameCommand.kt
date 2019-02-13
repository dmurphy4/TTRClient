package com.d.tickettoride.command.client

import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.service.ChooseGameService
import com.d.tickettoride.service.ErrorMessageService

class CCreateGameCommand (private val errorMessage:String?,
                          private val gameInfo: GameInfo) : ICommand {

    override fun execute() {
        if (errorMessage != null) {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        } else {
            ChooseGameService.instance.addGameToList(gameInfo)
        }
    }
}