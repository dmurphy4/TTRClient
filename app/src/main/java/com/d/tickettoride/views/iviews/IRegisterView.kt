package com.d.tickettoride.views.iviews

interface IRegisterView {
    fun startChooseGameActivity()
    fun displayErrorMessage(message: String?)
    fun enableRegister(enable: Boolean)
}