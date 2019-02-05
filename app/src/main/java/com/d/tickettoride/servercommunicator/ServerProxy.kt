package com.d.tickettoride.servercommunicator

import com.d.tickettoride.command.client.CPollerCommandList
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.service.LoginService
import com.google.gson.Gson
import okhttp3.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete
import org.jetbrains.anko.uiThread

class ServerProxy {

    private val client = OkHttpClient()
    private val url = "https://api.github.com"
    private val gson = Gson()
    val JSON = MediaType.parse("application/json; charset=utf-8")
    val poller = Poller()

    fun command(type: CommandType, data: String) {
        doAsync {
            val body = RequestBody.create(JSON, data)
            val request = Request.Builder().url(url)
                .addHeader("type", type.toString()).post(body).build()
            val response = client.newCall(request).execute()
            uiThread {
                // with {} - do something with response
                LoginService().setUserData("username we got back...")
            }
            onComplete {
                RootModel.instance.loggedIn = true
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