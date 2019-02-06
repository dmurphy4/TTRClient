package com.d.tickettoride.servercommunicator

import com.d.tickettoride.command.client.CLoginRegisterCommand
import com.d.tickettoride.command.client.CPollerCommandList
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ServerProxy {

    private val client = OkHttpClient()
    private val url = "http://10.37.208.17:8000/command"
    private val gson = Gson()
    private val JSON = MediaType.parse("application/json; charset=utf-8")

    fun command(type: CommandType, data: String) {
        doAsync {
            val body = RequestBody.create(JSON, data)
            val request = Request.Builder().url(url).addHeader("type", type.toString()).post(body).build()
            val response = client.newCall(request).execute()
            uiThread {
                // with {} - do something with response
                val respBody = response.body()!!.byteStream()
                val command = gson.fromJson(respBody.toString(), CLoginRegisterCommand::class.java)
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