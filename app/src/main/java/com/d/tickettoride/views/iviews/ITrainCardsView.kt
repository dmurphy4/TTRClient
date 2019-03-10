package com.d.tickettoride.views.iviews

import com.d.tickettoride.model.gameplay.TrainCarCardType

interface ITrainCardsView {
    fun updateCardAt(idx: Int, type: TrainCarCardType)
    fun updateDeckSize(idx: Int)
}