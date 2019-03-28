package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.Event
import com.d.tickettoride.model.gameplay.TrainCarDeck
import kotlin.properties.Delegates.observable

class Game(
    val gameInfo: GameInfo,
    var playerStats:ArrayList<PlayerInfo>,
    var turnOrder:List<String>,
    var trainCarDeck: TrainCarDeck,
    var destinationDeckSize: Int,
    val eventHistory:ArrayList<Event> = ArrayList()
) {
    var turn: Int = -1

    var statsChanged:Boolean by observable(false) { _, old, new ->
        onStatsChanged?.invoke(old, new)
    }

    var onEventAdded: ((Int, Int) -> Unit)? = null
    var onStatsChanged: ((Boolean, Boolean) -> Unit)? = null

    lateinit var board: Board

    fun addEvent(event:Event) {
        eventHistory.add(event)
        onEventAdded?.invoke(0, eventHistory.size)
    }

    fun getPlayerByUsername(username: String) : PlayerInfo? {
        for (player in playerStats) {
            if (player.username == username) {
                return player
            }
        }
        return null
    }

    fun updateTurn() {
        playerStats[turn].yourTurn = false
        turn = (turn + 1) % turnOrder.size
        playerStats[turn].yourTurn = true
        if (playerStats[turn].username == RootModel.instance.user!!.username) {
            RootModel.instance.user!!.yourTurn = true
        }
    }
}
