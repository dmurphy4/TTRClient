package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.User
import com.d.tickettoride.service.RegisterService
import com.d.tickettoride.views.IRegisterView

class RegisterPresenter(registerView: IRegisterView) : IRegisterPresenter {

    private val registerActivity = registerView

    override fun sendRegisterRequest(username: String, password: String, confirmPassword: String) {
        when {
            password != confirmPassword -> registerActivity.displayErrorMessage("Passwords do not match.")
            username.length < 5 -> registerActivity.displayErrorMessage("Username must be at least 5 characters.")
            else -> {

                val rootModel = RootModel.instance
                rootModel.onLogIn = { _, _ ->
                    //change to createGameView???
                    rootModel.user = User(username)
                    registerActivity.startChooseGameActivity()
                }
                rootModel.onErrorMessageGiven = {
                        _, new -> registerActivity.displayErrorMessage(new)
                }
                registerActivity.displayErrorMessage("You entered $username, $password")

                RegisterService().register(username, password)
                rootModel.loggedIn = true
            }
        }
    }
}