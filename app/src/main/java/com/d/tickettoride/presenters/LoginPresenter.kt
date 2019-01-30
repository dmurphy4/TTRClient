package com.d.tickettoride.presenters

import com.d.tickettoride.views.ILoginView

class LoginPresenter(private val loginActivity: ILoginView) : ILoginPresenter {

    override fun sendLoginRequest(username: String, password: String) {
        loginActivity.displayErrorMessage("You entered $username, $password")
        loginActivity.startChooseGameActivity()
    }

    override fun registerButtonClicked() {
        loginActivity.startRegisterActivity()
    }
}