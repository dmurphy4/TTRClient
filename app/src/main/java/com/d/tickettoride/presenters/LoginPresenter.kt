package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.service.LoginService
import com.d.tickettoride.views.ILoginView

class LoginPresenter(private val loginActivity: ILoginView) : ILoginPresenter {

    override fun sendLoginRequest(username: String, password: String) {
        val rootModel = RootModel.instance
        rootModel.onLogIn = { _, _ ->
            //change to createGameView???
        }

        loginActivity.displayErrorMessage("You entered $username, $password")

        LoginService().loginServer(username, password)

        loginActivity.startChooseGameActivity()
    }

    override fun registerButtonClicked() {
        loginActivity.startRegisterActivity()
    }
}