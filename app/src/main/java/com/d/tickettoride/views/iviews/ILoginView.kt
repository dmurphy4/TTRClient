package com.d.tickettoride.views.iviews

interface ILoginView {
    fun startChooseGameActivity()
    fun startRegisterActivity()
    fun displayErrorMessage(message: String?)
    fun enableLogIn(enable: Boolean)
}