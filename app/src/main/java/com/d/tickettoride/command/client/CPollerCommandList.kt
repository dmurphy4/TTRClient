package com.d.tickettoride.command.client

class CPollerCommandList(private val list:ArrayList<ICommand>) : ICommand {

    override fun execute() {
        for (command:ICommand in list) {
            command.execute()
        }
    }

}