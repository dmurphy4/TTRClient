package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.servercommunicator.CommandData
import com.d.tickettoride.service.ErrorMessageService

class CommandListResponse(private val errorMessage:String?, private val commandList:ArrayList<CommandData>?) :
    GenericResponse(errorMessage) {

    override fun execute() {
        if (errorMessage == null) {
            if (commandList != null) {
                for (command:CommandData in commandList) {
                    command.execute()
                }
            } else {
                println("Aaron Kline")
            }
        }
        else {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
    }

}