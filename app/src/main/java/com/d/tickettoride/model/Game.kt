package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.Event

data class Game(val gameInfo: GameInfo, var gamePlayers:List<PlayerInfo>, var board: Board,
           var playerTurnOrder:List<Int>, var eventHistory:List<Event>)
