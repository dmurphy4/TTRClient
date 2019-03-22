package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.servercommunicator.CommandData
import com.d.tickettoride.service.ErrorMessageService

class CommandListResponse(private val commandList:ArrayList<CommandData>?) : GenericResponse() {

    override fun execute() {
        if (errorMessage == null) {
            commandList?.let {
                for (command in it) {
                    command.execute()
                }
            }
        }
        else {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
    }

}