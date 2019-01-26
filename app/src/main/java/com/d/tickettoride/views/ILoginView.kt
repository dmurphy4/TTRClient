package com.d.tickettoride.views

interface ILoginView {
    fun startChooseGameActivity()
    fun startRegisterActivity()
    fun displayErrorMessage(message: String)
}