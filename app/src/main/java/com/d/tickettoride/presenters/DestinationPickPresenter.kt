package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.presenters.ipresenters.IDestinationPickPresenter

class DestinationPickPresenter : IDestinationPickPresenter {

    init {
        RootModel.instance.onDestinationCardsGiven = { _, new ->
            //do something with the new list of cards. present them however
        }
    }
}