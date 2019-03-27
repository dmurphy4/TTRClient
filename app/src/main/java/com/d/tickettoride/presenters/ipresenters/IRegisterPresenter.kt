package com.d.tickettoride.presenters.ipresenters

interface IRegisterPresenter {
    fun sendRegisterRequest(username: String, password: String)
    fun setHost(host: String)
    fun setPort(port: String)
}