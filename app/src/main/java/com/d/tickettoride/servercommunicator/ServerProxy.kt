package com.d.tickettoride.servercommunicator

import com.d.tickettoride.command.server.*
import com.google.gson.Gson
import okhttp3.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ServerProxy {

    private val client = OkHttpClient()
    private val url = "http://10.37.147.18:8001/"
    private val gson = Gson()

    fun login(data:SLoginCommand) {
        val formBody = FormBody.Builder()
            .add("data", "Hey Dallin!").build()
        val request = Request.Builder().url(url).post(formBody).build()

    }

    fun register(data:SRegisterCommand) {

    }

    fun createGame(data:SCreateGameCommand) {

    }

    fun joinGame(data:SJoinGameCommand) {
        doAsync {
            val command = CommandData(CommandType.S_JOIN_GAME, gson.toJson(data))
            val JSON = MediaType.parse("application/json; charset=utf-8")
            val body = RequestBody.create(JSON, Gson().toJson(command))
            val request = Request.Builder().url(url).post(body).build()
            val response = client.newCall(request).execute()
            uiThread {
                val r = response
                r.body()
            }
        }

//        val formBody = FormBody.Builder()
//            .add("type", CommandType.C_JOIN_GAME.toString())
//            .add("data", Gson().toJson(data)).build()
//        val request = Request.Builder().url(url).post(formBody).build()
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