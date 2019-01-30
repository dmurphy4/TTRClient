package com.d.tickettoride.service

import com.d.tickettoride.command.server.SLoginCommand
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.servercommunicator.ServerProxy
import org.jetbrains.anko.doAsync

class LoginService {

    fun loginServer(userName:String, password:String) {
        doAsync {
            val loginComm = SLoginCommand(userName, password)

            val serverProxy = ServerProxy()
            serverProxy.login(loginComm)
        }

    }

    fun loginUser(userName:String?, success:Boolean, errorMessage:String?) {
        var rootModel = RootModel.instance
        rootModel.loggedIn = true
    }
}