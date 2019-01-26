package com.d.tickettoride.servercommunicator

class Poller {
    fun poll() {
        val sp = ServerProxy()
        sp.executeExistingCommands()
    }
}