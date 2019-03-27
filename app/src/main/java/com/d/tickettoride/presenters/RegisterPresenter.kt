package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.presenters.ipresenters.IRegisterPresenter
import com.d.tickettoride.service.LoginService
import com.d.tickettoride.views.iviews.IRegisterView

class RegisterPresenter(private val registerActivity: IRegisterView,
                        private val loginService: LoginService = LoginService.instance) :
    IRegisterPresenter {

    init {
        RootModel.instance.onErrorMessageGiven = { _, message ->
            registerActivity.displayErrorMessage(message)
        }
    }

    override fun sendRegisterRequest(username: String, password: String) {
        RootModel.instance.onLogIn = { _, loggedIn ->
            if (loggedIn) {
                registerActivity.startChooseGameActivity()
            } else {
                registerActivity.enableRegister(true)
            }
        }
        registerActivity.enableRegister(false)
        loginService.register(username, password)
    }

    override fun setHost(host: String) {
        RootModel.instance.host = host
    }

    override fun setPort(port: String) {
        RootModel.instance.port = port
    }
}