package com.d.tickettoride.command.client

import com.d.tickettoride.servercommunicator.CommandData

class CPollerCommandList(private val list:ArrayList<CommandData>?) : ICommand {

    override fun execute() {
        if (list != null) {
            for (command:CommandData in list) {
                command.execute()
            }
        }
        else {
            println("Aaron Kline")
        }
    }

}