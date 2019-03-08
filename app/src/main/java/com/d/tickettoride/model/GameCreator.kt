package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.Event

class GameCreator(val gameInfo: GameInfo, var playerStats:ArrayList<PlayerInfo>, var board: Board,
                  var turnOrder:List<String>, var eventHistory:ArrayList<Event> = ArrayList()) {

    fun createGame() : Game {
        return Game(gameInfo, playerStats, board, turnOrder, eventHistory)
    }
}