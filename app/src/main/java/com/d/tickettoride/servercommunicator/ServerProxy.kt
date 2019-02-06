package com.d.tickettoride.servercommunicator

import com.d.tickettoride.command.client.*
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ServerProxy {
    companion object {
        val client = OkHttpClient()
    }

    private val url = "http://10.37.208.17:8000/command"
    private val JSON = MediaType.parse("application/json; charset=utf-8")

    fun command(type: CommandType, data: String) {
        doAsync {
            val body = RequestBody.create(JSON, data)
            val request = Request.Builder().url(url).addHeader("type", type.toString()).post(body).build()
            val response = client.newCall(request).execute()
            uiThread {
                val classType = when (type) {
                    CommandType.S_LOGIN, CommandType.S_REGISTER -> CLoginRegisterCommand::class.java
                    CommandType.S_CREATE_GAME -> CCreateGameCommand::class.java
                    CommandType.S_JOIN_GAME -> CJoinGameCommand::class.java
                    CommandType.S_POLL -> CPollerCommandList::class.java
                    else -> ICommand::class.java
                }
                val gson = Gson()
                val respBody = response.body()!!.byteStream()
                val command = gson.fromJson(respBody.toString(), classType)
                command.execute()
            }
        }
    }

    fun executeExistingCommands() {
        getCommands()?.execute()
    }

    private fun getCommands() : CPollerCommandList? {
        return null
    }

}