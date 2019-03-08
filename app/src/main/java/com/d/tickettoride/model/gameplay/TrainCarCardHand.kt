package com.d.tickettoride.model.gameplay

class TrainCarCardHand(var cards:List<TrainCarCard>, var cardMap:MutableMap<TrainCarCardType, Int> = HashMap()) : IHand {

    init {
        for (card in cards) {
            if (cardMap.containsKey(card.type)) {
                cardMap[card.type]!!.plus(1)
            }
            else {
                cardMap[card.type] = 1
            }
        }
    }

    override fun draw(cardsDrawn: List<ICard>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playCards() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}