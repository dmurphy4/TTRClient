package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.User
import com.d.tickettoride.service.LoginService
import com.d.tickettoride.views.ILoginView

class LoginPresenter(private val loginActivity: ILoginView) : ILoginPresenter {

    override fun sendLoginRequest(username: String, password: String) {
        val rootModel = RootModel.instance
        rootModel.onLogIn = { _, _ ->
            rootModel.user = User(username)
            loginActivity.startChooseGameActivity()
        }
        rootModel.onErrorMessageGiven = {
            _, new -> loginActivity.displayErrorMessage(new)
        }

        loginActivity.displayErrorMessage("You entered $username, $password")

        LoginService().loginServer(username, password)
        rootModel.loggedIn = true
    }

    override fun registerButtonClicked() {
        loginActivity.startRegisterActivity()
    }
}