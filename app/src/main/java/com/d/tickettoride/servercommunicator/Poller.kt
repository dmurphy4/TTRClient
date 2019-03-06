package com.d.tickettoride.servercommunicator

import com.d.tickettoride.model.RootModel
import java.util.Timer
import java.util.TimerTask

class Poller {
    var timer: Timer = Timer()
    init {
        timer.schedule(PollTask(), 0, 1 * 2000)
    }

    class PollTask : TimerTask() {
        override fun run() {
            ServerProxy().command(CommandType.S_POLL, "{ \"username\": ${RootModel.instance.user?.username} }")
        }
    }
}