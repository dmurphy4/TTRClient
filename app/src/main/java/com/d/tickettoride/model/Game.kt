package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.Event

class Game(val gameInfo: GameInfo, var gamePlayers:ArrayList<PlayerInfo>, var board: Board, var eventHistory:List<Event>) {

}
