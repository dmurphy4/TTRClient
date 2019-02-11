package com.d.tickettoride.command.client

import com.d.tickettoride.servercommunicator.CommandData

class CPollerCommandList(private val commandList:ArrayList<CommandData>?) : ICommand {

    override fun execute() {
        if (commandList != null) {
            for (command:CommandData in commandList) {
                command.execute()
            }
        }
        else {
            println("Aaron Kline")
        }
    }

}