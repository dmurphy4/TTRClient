package com.d.tickettoride.command.client

class CPollerCommandList(private val list:ArrayList<ICommand>?) : ICommand {

    override fun execute() {
        if (list != null) {
            for (command: ICommand in list) {
                command.execute()
            }
        }
        else {
            println("Aaron Kline")
        }
    }

}