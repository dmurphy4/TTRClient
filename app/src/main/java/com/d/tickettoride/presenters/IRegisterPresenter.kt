package com.d.tickettoride.presenters

interface IRegisterPresenter {
    fun sendRegisterRequest(username: String, password: String, confirmPassword: String)
}