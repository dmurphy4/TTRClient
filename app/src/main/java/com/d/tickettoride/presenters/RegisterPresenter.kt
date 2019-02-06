package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.service.LoginService
import com.d.tickettoride.views.IRegisterView

class RegisterPresenter(private val registerActivity: IRegisterView,
                        private val loginService: LoginService = LoginService()) : IRegisterPresenter {

    init {
        RootModel.instance.onErrorMessageGiven = { _, message ->
            registerActivity.displayErrorMessage(message)
        }
    }

    override fun sendRegisterRequest(username: String, password: String, confirmPassword: String) {
        RootModel.instance.onLogIn = { _, loggedIn ->
            if (loggedIn) {
                registerActivity.startChooseGameActivity()
            } else {
                registerActivity.enableRegister(true)
            }
        }
        registerActivity.enableRegister(false)
        loginService.setUserData(username)
        loginService.register(username, password, confirmPassword)
    }
}