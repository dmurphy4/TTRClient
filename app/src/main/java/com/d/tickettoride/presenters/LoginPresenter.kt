package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.User
import com.d.tickettoride.service.LoginService
import com.d.tickettoride.views.ILoginView

class LoginPresenter(private val loginActivity: ILoginView,
                     private val loginService: LoginService = LoginService()) : ILoginPresenter {

    init {
        RootModel.instance.onErrorMessageGiven = { _, message ->
            loginActivity.displayErrorMessage(message)
        }
    }

    override fun sendLoginRequest(username: String, password: String) {
        RootModel.instance.onLogIn = { _, loggedIn ->
            if (loggedIn) {
                loginActivity.startChooseGameActivity()
                loginActivity.displayErrorMessage("Username is ${RootModel.instance.user!!.userName}")
            } else {
                loginActivity.enableLogIn(true)
            }
        }
        loginActivity.enableLogIn(false)
        loginService.setUserData(username)
        loginService.loginServer(username, password)
    }

    override fun registerButtonClicked() {
        loginActivity.startRegisterActivity()
    }
}