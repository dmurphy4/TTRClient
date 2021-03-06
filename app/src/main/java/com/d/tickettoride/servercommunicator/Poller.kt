package com.d.tickettoride.servercommunicator

import com.d.tickettoride.servercommunicator.command.server.ServerCommand
import com.d.tickettoride.model.RootModel
import java.util.Timer
import java.util.TimerTask

class Poller {
    private val timer = Timer()
    init {
        timer.schedule(PollTask(), 0, 1 * 1000)
    }

    class PollTask : TimerTask() {
        private val proxy = ServerProxy()

        override fun run() {
            proxy.command(ServerCommand.Poll(RootModel.instance.user!!.username))
        }
    }
}