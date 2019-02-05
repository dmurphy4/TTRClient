package com.d.tickettoride.service

import com.d.tickettoride.model.RootModel

class ErrorMessageService {

    fun postErrorMessage(errorMessage:String) {
        val rootModel = RootModel.instance
        rootModel.errorMessage = errorMessage
    }
}