package com.d.tickettoride.service

import com.d.tickettoride.command.server.SLoginCommand
import com.d.tickettoride.command.server.SRegisterCommand
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.User
import com.d.tickettoride.servercommunicator.CommandType
import com.d.tickettoride.servercommunicator.ServerProxy
import com.google.gson.Gson
import org.jetbrains.anko.doAsync

class LoginService(private val proxy: ServerProxy = ServerProxy()) {

    fun loginServer(username:String, password:String) {
        val data = Gson().toJson(SLoginCommand(username, password))
        proxy.command(CommandType.S_LOGIN, data)
    }

    fun loginUser() {
        var rootModel = RootModel.instance
        rootModel.loggedIn = true
    }

    fun setUserData(username: String) {
        RootModel.instance.user = User(username)
    }

    fun register(userName: String, password: String, confirmPassword: String) {
        val serverCommand = SRegisterCommand(userName, password, confirmPassword)
        proxy.command(CommandType.S_REGISTER, Gson().toJson(serverCommand))
    }
}