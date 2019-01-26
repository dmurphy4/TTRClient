package com.d.tickettoride.presenters

interface ILoginPresenter {
    fun sendLoginRequest(username: String, password: String)
    fun registerButtonClicked()
}