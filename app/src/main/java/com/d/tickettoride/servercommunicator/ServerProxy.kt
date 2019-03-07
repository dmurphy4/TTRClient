package com.d.tickettoride.servercommunicator

import com.d.tickettoride.command.server.ServerCommand
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
    private val url = "http://10.37.115.211:8000/command"
    private val JSON = MediaType.parse("application/json; charset=utf-8")

    fun command(command: ServerCommand) {
        val data = command.toJson()
        val type = command.type()
        println("HERE'S THE DATA GOING TO SERVER:")
        println(data)
        doAsync {
            val body = RequestBody.create(JSON, data)
            val request = Request.Builder().url(url).addHeader("type", type.toString()).post(body).build()
            val response = client.newCall(request).execute()
            val respBody = response.body()!!.string()
            uiThread {
                val gson = Gson()
                println("HERE'S THE RESPONSE FROM THE SERVER:")
                println(respBody)
                val clientCommand = gson.fromJson(respBody, command.responseType())
                clientCommand.execute()
            }
        }
    }
}