package com.d.tickettoride.command.client

import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.model.Player
import com.d.tickettoride.model.PlayerColor
import com.d.tickettoride.service.ErrorMessageService
import com.d.tickettoride.service.JoinGameService

class CJoinGameCommand (private val errorMessage:String?,
                        private val success:Boolean,
                        private val playerColor:String,
                        private val userName:String,
                        private val gameName:String,
                        private val numPlayers:Int) : ICommand {

    override fun execute() {
        if (success) {
            val player = Player(userName, PlayerColor.valueOf(playerColor.toUpperCase()))
            val gameInfo = GameInfo(gameName, numPlayers)
            JoinGameService.instance.setGameData(gameInfo, player)
        }
        else {
            ErrorMessageService.instance.postErrorMessage(errorMessage!!)
        }
    }
}