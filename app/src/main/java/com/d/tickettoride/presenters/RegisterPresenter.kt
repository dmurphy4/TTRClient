package com.d.tickettoride.presenters

import com.d.tickettoride.views.IRegisterView

class RegisterPresenter(registerView: IRegisterView) : IRegisterPresenter {

    private val registerActivity = registerView

    override fun sendRegisterRequest(username: String, password: String, confirmPassword: String) {
        when {
            password != confirmPassword -> registerActivity.displayErrorMessage("Passwords do not match.")
            username.length < 5 -> registerActivity.displayErrorMessage("Username must be at least 5 characters.")
            else -> {
                registerActivity.displayErrorMessage("You entered $username, $password")
                registerActivity.startChooseGameActivity()
            }
        }
    }
}