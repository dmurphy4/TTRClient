package com.d.tickettoride.servercommunicator

import com.d.tickettoride.model.RootModel
import java.util.*

class Poller {
    var timer: Timer = Timer()
    init {
        timer.schedule(PollTask(), 0, 1 * 1000)
    }

    class PollTask : TimerTask() {
        override fun run() {
            val proxy = ServerProxy()
            proxy.command(CommandType.S_POLL, "{ userName: ${RootModel.instance.user?.userName} }")
        }
    }
}