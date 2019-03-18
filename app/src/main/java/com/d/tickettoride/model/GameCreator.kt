package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.*

class GameCreator(val gameInfo: GameInfo, var playerStats:ArrayList<PlayerInfo>, var board: Board,
                  var turnOrder:List<String>, var eventHistory:ArrayList<Event> = ArrayList()) {

    fun createGame() : Game {
        val game = Game(gameInfo, playerStats, board, turnOrder, eventHistory)
        game.prepareCards()
        return game
    }
}