package com.d.tickettoride.model.gameplay

import kotlin.properties.Delegates.observable


class TrainCarCardHand(val cards:List<TrainCarCard>) : IHand {

    lateinit var cardMap:HashMap<TrainCarCardType, Int>

    override fun draw(cardsDrawn: List<ICard>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playCards() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setUpMap() {
        cardMap = HashMap()
        cardMap[TrainCarCardType.RED] = 0
        cardMap[TrainCarCardType.BLACK] = 0
        cardMap[TrainCarCardType.BLUE] = 0
        cardMap[TrainCarCardType.GREEN] = 0
        cardMap[TrainCarCardType.YELLOW] = 0
        cardMap[TrainCarCardType.ORANGE] = 0
        cardMap[TrainCarCardType.WHITE] = 0
        cardMap[TrainCarCardType.PURPLE] = 0
        cardMap[TrainCarCardType.LOCOMOTIVE] = 0

        for (card in cards) {
            cardMap[card.type] = cardMap[card.type]!! + 1
        }
    }
}