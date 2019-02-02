package com.d.tickettoride.service

import com.d.tickettoride.command.server.SLoginCommand
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.User
import com.d.tickettoride.servercommunicator.CommandType
import com.d.tickettoride.servercommunicator.ServerProxy
import com.google.gson.Gson
import org.jetbrains.anko.doAsync

class LoginService {

    fun loginServer(username:String, password:String) {
        val data = Gson().toJson(SLoginCommand(username, password))
        ServerProxy().command(CommandType.S_LOGIN, data)
    }

    fun loginUser(success:Boolean, errorMessage:String?) {
        var rootModel = RootModel.instance
        if (success) {
            rootModel.loggedIn = true
        }
        else {
            rootModel.errorMessage = errorMessage
        }
    }

    fun setUserData(username: String) {
        RootModel.instance.user = User(username)
    }
}