package com.d.tickettoride.servercommunicator

import com.d.tickettoride.command.server.*
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.RequestBody
import java.net.URL

class ServerProxy {

    fun login(data:SLoginCommand) {
        //val formBody:RequestBody = FormBody.Builder().add("username", data.userName).add("password", data.password).build()

        //val request:Request = Request.Builder().url().post(formBody).build()



    }

    fun register(data:SRegisterCommand) {

    }

    fun createGame(data:SCreateGameCommand) {

    }

    fun joinGame(data:SJoinGameCommand) {

    }

    fun beginPlay(data:SBeginPlayCommand) {

    }

    fun executeExistingCommands() {
        getCommands()
        executeCommands()

    }

    private fun getCommands() {

    }

    private fun executeCommands() {

    }

}