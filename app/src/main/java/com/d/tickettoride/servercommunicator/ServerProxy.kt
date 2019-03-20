package com.d.tickettoride.servercommunicator

import com.d.tickettoride.servercommunicator.command.server.ServerCommand
import com.d.tickettoride.servercommunicator.response.*
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

    private val url = "http://192.168.253.253:8000/command"

    private val JSON = MediaType.parse("application/json; charset=utf-8")

    fun command(command: ServerCommand) {
        val data = command.toJson()
        println("HERE'S THE DATA GOING TO SERVER:")
        println(data)
        doAsync {
            val body = RequestBody.create(JSON, data)
            val request = Request.Builder().url(url).header("Accept-Encoding", "identity").addHeader("type", command.type().toString()).post(body).build()
            val response = client.newCall(request).execute()
            val respBody = response.body()!!.string()
            uiThread {
                val type = when(command) {
                    is ServerCommand.ChooseFirstDestinationHand -> FirstDestinationHandResponse::class.java
                    is ServerCommand.JoinGame -> JoinGameResponse::class.java
                    is ServerCommand.Login, is ServerCommand.Register -> LoginRegisterResponse::class.java
                    is ServerCommand.Poll -> CommandListResponse::class.java
                    is ServerCommand.DrawDestinationCards -> DrawDestinationCardResponse::class.java
                    is ServerCommand.ChooseDestinationCard -> ReceiveMoreDestinationsResponse::class.java
                    else -> GenericResponse::class.java
                }
                val gson = Gson()
                println("HERE'S THE RESPONSE FROM THE SERVER:")
                println(respBody)
                val clientResponse = gson.fromJson(respBody, type)
                clientResponse.execute()
            }
        }
    }
}