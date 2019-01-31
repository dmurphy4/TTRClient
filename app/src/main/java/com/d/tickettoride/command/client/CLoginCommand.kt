package com.d.tickettoride.command.client

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.User

class CLoginCommand (val userName:String, val success:Boolean, val errorMessage:String?) : ICommand {

    // If you log in, then it doesn't really affect the other players.

    // So, we don't need to let them know that you logged in.
    // But this has the same functionality, so do we need both?
    // The error message will explain why it didn't work...
    override fun execute() {
        if (success) {
            val rootModel = RootModel.instance
            rootModel.loggedIn = true
            rootModel.user = User(userName)
        }
        else {
            // do we have an error attribute so that the presenter can listen for it and then present it?

        }
    }
}