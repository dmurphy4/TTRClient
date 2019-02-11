package com.d.tickettoride.views

interface IRegisterView {
    fun startChooseGameActivity()
    fun displayErrorMessage(message: String?)
    fun enableRegister(enable: Boolean)
}