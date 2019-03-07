package com.d.tickettoride.service

import com.d.tickettoride.servercommunicator.command.server.ServerCommand
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.User
import com.d.tickettoride.servercommunicator.ServerProxy

class LoginService(private val proxy: ServerProxy = ServerProxy()) {

    companion object {
        val instance = LoginService()
    }

    fun loginServer(username:String, password:String) {
        proxy.command(ServerCommand.Login(username, password))
    }

    fun loginUser(username:String) {
        setUserData(username)
        RootModel.instance.loggedIn = true
    }

    private fun setUserData(username: String) {
        RootModel.instance.user = User(username, null, null, null)
    }

    fun register(username: String, password: String) {
        proxy.command(ServerCommand.Register(username, password))
    }
}