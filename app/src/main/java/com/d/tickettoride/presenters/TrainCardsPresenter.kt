package com.d.tickettoride.presenters

import com.d.tickettoride.R
import com.d.tickettoride.model.gameplay.TrainCarCardType
import com.d.tickettoride.presenters.ipresenters.ITrainCardsPresenter
import com.d.tickettoride.service.TrainCardService
import com.d.tickettoride.views.iviews.ITrainCardsView

class TrainCardsPresenter(private val trainCardsFragment: ITrainCardsView): ITrainCardsPresenter {

    private val trainCardService: TrainCardService = TrainCardService.instance

    override fun replaceFaceUpCard(idx: Int) {
        trainCardService.setFaceUpChangedListener { index, type ->
            trainCardsFragment.updateCardAt(index, getDrawable(type))
            trainCardsFragment.updateDeckSize(trainCardService.drawPileSize())
        }
        trainCardService.takeFaceUpCard(idx)
    }

    override fun drawFromDeck() {
        trainCardService.drawFromDeck()
        trainCardsFragment.updateDeckSize(trainCardService.drawPileSize())
    }

    private fun getDrawable(type: TrainCarCardType): Int {
        return when(type) {
            TrainCarCardType.BLACK -> R.drawable.cardblack
            TrainCarCardType.RED -> R.drawable.cardred
            TrainCarCardType.PURPLE -> R.drawable.cardpink
            TrainCarCardType.GREEN -> R.drawable.cardgreen
            TrainCarCardType.YELLOW -> R.drawable.cardyellow
            TrainCarCardType.ORANGE -> R.drawable.cardorange
            TrainCarCardType.WHITE -> R.drawable.cardwhite
            TrainCarCardType.BLUE -> R.drawable.cardblue
            TrainCarCardType.LOCOMOTIVE -> R.drawable.cardlocomotive
        }
    }
}