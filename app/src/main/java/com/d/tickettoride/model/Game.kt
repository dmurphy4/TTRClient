package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.Event
import kotlin.properties.Delegates.observable

class Game(val gameInfo: GameInfo, var playerStats:ArrayList<PlayerInfo>, var board: Board,
                var turnOrder:List<String>, var eventHistory:ArrayList<Event> = ArrayList()) {

    var eventListLength:Int by observable(0) { _, old, new ->
        onEventAdded?.invoke(old, new)
    }

    var onEventAdded: ((Int, Int) -> Unit)? = null

    var turn: Int by observable(0) {_, old, new ->
        onTurnChanged?.invoke(old, new)
    }

    var onTurnChanged: ((Int, Int) -> Unit)? = null

    var statsChanged:Boolean by observable(false) { _, old, new ->
        onStatsChanged?.invoke(old, new)
    }

    var onStatsChanged: ((Boolean, Boolean) -> Unit)? = null

    fun addEvent(event:Event) {
        eventHistory.add(event)
        eventListLength = eventHistory.size
    }

    fun prepareCards() {
        board.prepFaceUpCards()
    }

    fun updateTurn() {
        playerStats[turn].yourTurn = false
        turn = (turn + 1) % turnOrder.size
        playerStats[turn].yourTurn = true
    }
}
