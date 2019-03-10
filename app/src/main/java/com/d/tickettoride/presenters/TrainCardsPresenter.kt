package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.presenters.ipresenters.ITrainCardsPresenter
import com.d.tickettoride.views.iviews.ITrainCardsView

class TrainCardsPresenter(val trainCardsFragment: ITrainCardsView): ITrainCardsPresenter {

    override fun replaceFaceUpCard(idx: Int) {
        RootModel.instance.game!!.board.trainDeck.onFaceUpChanged = { index, type ->
            val deckSize = RootModel.instance.game!!.board.trainDeck.drawPile.size
            trainCardsFragment.updateCardAt(index, type)
            trainCardsFragment.updateDeckSize(deckSize)
        }
        RootModel.instance.game!!.board.trainDeck.replaceFaceUpCard(idx)
    }

    override fun drawFromDeck() {

    }

}