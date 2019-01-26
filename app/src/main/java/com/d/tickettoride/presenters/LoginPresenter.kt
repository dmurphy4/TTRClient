package com.d.tickettoride.presenters

import com.d.tickettoride.views.ILoginView

class LoginPresenter(loginView: ILoginView) : ILoginPresenter {

    private val loginActivity = loginView

    override fun sendLoginRequest(username: String, password: String) {
        loginActivity.displayErrorMessage("You entered $username, $password")
        loginActivity.startChooseGameActivity()
    }

    override fun registerButtonClicked() {
        loginActivity.startRegisterActivity()
    }
}