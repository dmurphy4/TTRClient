package com.d.tickettoride.servercommunicator.command.client

class CChangeStatsCommand(private val changes: List<StatsChange>, val username: String) : ICommand {

    override fun execute() {
        for (change in changes) {
            change.execute(username)
        }
    }
}