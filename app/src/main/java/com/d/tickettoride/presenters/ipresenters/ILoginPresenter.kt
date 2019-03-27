package com.d.tickettoride.presenters.ipresenters

interface ILoginPresenter {
    fun sendLoginRequest(username: String, password: String)
    fun registerButtonClicked()
    fun setHost(host: String)
    fun setPort(port: String)
}