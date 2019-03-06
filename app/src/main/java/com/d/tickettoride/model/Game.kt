package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.Event
import kotlin.properties.Delegates

class Game(val gameInfo: GameInfo, var gamePlayers:Map<String, PlayerInfo>, var board: Board,
                var turnOrder:List<String>, var eventHistory:MutableList<Event>) {

    var eventListLength:Int by Delegates.observable(0) { _, old, new ->
        onEventAdded?.invoke(old, new)
    }

    var onEventAdded: ((Int, Int) -> Unit)? = null

    fun addEvent(event:Event) {
        eventHistory.add(event)
        eventListLength = eventHistory.size
    }

    fun prepareCards() {
        board.prepFaceUpCards()
    }
}
