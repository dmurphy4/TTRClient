package com.d.tickettoride.command.server

import com.d.tickettoride.model.gameplay.DestinationCard
import com.d.tickettoride.model.gameplay.Event
import com.d.tickettoride.command.CommandType
import com.google.gson.Gson

sealed class ServerCommand {
    data class ChooseDestinationCard(var player:String, var chosenCards:List<DestinationCard>): ServerCommand() {
        override fun type() = CommandType.S_ASSIGN_DEST
    }

    data class CreateGame(val gameName:String, val numPlayers:Int, val username:String): ServerCommand() {
        override fun type() = CommandType.S_CREATE_GAME
    }

    data class JoinGame (val gameName:String, val username:String): ServerCommand() {
        override fun type() = CommandType.S_JOIN_GAME
    }

    data class Login (val username:String, val password:String): ServerCommand() {
        override fun type() = CommandType.S_LOGIN
    }

    data class Poll(private val username:String): ServerCommand() {
        override fun type() = CommandType.S_POLL
    }

    data class Register(val username:String, val password:String): ServerCommand() {
        override fun type() = CommandType.S_REGISTER
    }

    data class SendMessage(var event: Event): ServerCommand() {
        override fun type() = CommandType.S_SEND_MESSAGE
    }

    abstract fun type(): CommandType

    fun toJson(): String {
        return Gson().toJson(this)
    }
}