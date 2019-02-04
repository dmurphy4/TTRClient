package com.d.tickettoride.servercommunicator

import java.util.*

class Poller {
    var timer: Timer = Timer()
    init {
        timer.schedule(PollTask(), 0, 1 * 1000)
    }

    class PollTask : TimerTask {
        constructor()

        override fun run() {
            ServerProxy().executeExistingCommands()
        }
    }
}