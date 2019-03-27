package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.presenters.ipresenters.ILoginPresenter
import com.d.tickettoride.service.LoginService
import com.d.tickettoride.views.iviews.ILoginView

class LoginPresenter(private val loginActivity: ILoginView,
                     private val loginService: LoginService = LoginService.instance) :
    ILoginPresenter {

    init {
        RootModel.instance.onErrorMessageGiven = { _, message ->
            loginActivity.displayErrorMessage(message)
        }
    }

    override fun sendLoginRequest(username: String, password: String) {
        RootModel.instance.onLogIn = { _, loggedIn ->
            if (loggedIn) {
                loginActivity.startChooseGameActivity()
                loginActivity.displayErrorMessage("Username is ${RootModel.instance.user!!.username}")
            } else {
                loginActivity.enableLogIn(true)
            }
        }
        loginActivity.enableLogIn(false)
        loginService.loginServer(username, password)
    }

    override fun registerButtonClicked() {
        loginActivity.startRegisterActivity()
    }

    override fun setHost(host: String) {
        RootModel.instance.host = host
    }

    override fun setPort(port: String) {
        RootModel.instance.port = port
    }
}