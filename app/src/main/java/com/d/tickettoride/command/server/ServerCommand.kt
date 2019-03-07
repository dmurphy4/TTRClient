package com.d.tickettoride.command.server

import com.d.tickettoride.command.client.*
import com.d.tickettoride.model.gameplay.DestinationCard
import com.d.tickettoride.model.gameplay.Event
import com.d.tickettoride.servercommunicator.CommandType
import com.google.gson.Gson

sealed class ServerCommand {
    data class ChooseDestinationCard(var player:String, var chosenCards:List<DestinationCard>): ServerCommand() {
        override fun type() = CommandType.S_ASSIGN_DEST
        override fun responseType() = CChooseDestCardCommand::class.java
    }

    data class CreateGame(val gameName:String, val numPlayers:Int, val username:String): ServerCommand() {
        override fun type() = CommandType.S_CREATE_GAME
        override fun responseType() = CCreateGameCommand::class.java
    }

    data class JoinGame (val gameName:String, val username:String): ServerCommand() {
        override fun type() = CommandType.S_JOIN_GAME
        override fun responseType() = CJoinGameCommand::class.java
    }

    data class Login (val username:String, val password:String): ServerCommand() {
        override fun type() = CommandType.S_LOGIN
        override fun responseType() = CLoginRegisterCommand::class.java
    }

    data class Poll(private val username:String): ServerCommand() {
        override fun type() = CommandType.S_POLL
        override fun responseType() = CPollerCommandListCommand::class.java
    }

    data class Register(val username:String, val password:String): ServerCommand() {
        override fun type() = CommandType.S_REGISTER
        override fun responseType() = CLoginRegisterCommand::class.java
    }

    data class SendMessage(var event: Event): ServerCommand() {
        override fun type() = CommandType.S_SEND_MESSAGE
        override fun responseType() = CChatCommand::class.java
    }

    abstract fun type(): CommandType
    abstract fun responseType(): Class<out ICommand>
    fun toJson(): String {
        return Gson().toJson(this)
    }
}