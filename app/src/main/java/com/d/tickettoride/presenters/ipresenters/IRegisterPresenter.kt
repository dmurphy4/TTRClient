package com.d.tickettoride.presenters.ipresenters

interface IRegisterPresenter {
    fun sendRegisterRequest(username: String, password: String)
}