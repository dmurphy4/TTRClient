package com.d.tickettoride.presenters

import com.d.tickettoride.presenters.ipresenters.ITrainCardsPresenter
import com.d.tickettoride.service.TrainCardService
import com.d.tickettoride.views.iviews.ITrainCardsView

class TrainCardsPresenter(private val trainCardsFragment: ITrainCardsView): ITrainCardsPresenter {

    private val trainCardService: TrainCardService = TrainCardService.instance

    override fun replaceFaceUpCard(idx: Int) {
        trainCardService.setFaceUpChangedListener { index, type ->
            val deckSize = trainCardService.drawPileSize()
            trainCardsFragment.updateCardAt(index, type)
            trainCardsFragment.updateDeckSize(deckSize)
        }
        trainCardService.takeFaceUpCard(idx)
    }

    override fun drawFromDeck() {
        trainCardService.drawFromDeck()
        trainCardsFragment.updateDeckSize(trainCardService.drawPileSize())
    }

}