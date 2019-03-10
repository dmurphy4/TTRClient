package com.d.tickettoride.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.d.tickettoride.R
import com.d.tickettoride.model.gameplay.TrainCarCardType
import com.d.tickettoride.presenters.TrainCardsPresenter
import com.d.tickettoride.presenters.ipresenters.ITrainCardsPresenter
import com.d.tickettoride.views.iviews.ITrainCardsView
import kotlinx.android.synthetic.main.fragment_train_cards.*

class TrainCardsFragment : Fragment(), ITrainCardsView {

    val trainCardsPresenter: ITrainCardsPresenter = TrainCardsPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_train_cards, container, false)

        view.post {
            image_card_1.setImageResource(R.drawable.cardblue)
            image_card_1.setOnClickListener {
                trainCardsPresenter.replaceFaceUpCard(0)
            }
            image_card_2.setImageResource(R.drawable.cardblack)
            image_card_2.setOnClickListener {
                trainCardsPresenter.replaceFaceUpCard(1)
            }
            image_card_3.setImageResource(R.drawable.cardlocomotive)
            image_card_3.setOnClickListener {
                trainCardsPresenter.replaceFaceUpCard(2)
            }
            image_card_4.setImageResource(R.drawable.cardgreen)
            image_card_4.setOnClickListener {
                trainCardsPresenter.replaceFaceUpCard(3)
            }
            image_card_5.setImageResource(R.drawable.cardred)
            image_card_5.setOnClickListener {
                trainCardsPresenter.replaceFaceUpCard(4)
            }
            train_deck_count.text = "Cards Left: 105"
        }

        return view
    }

    override fun updateCardAt(idx: Int, type: TrainCarCardType) {
        val res = getDrawable(type)
        if (idx == 0) {
            image_card_1.setImageResource(res)
        }
        if (idx == 1) {
            image_card_2.setImageResource(res)
        }
        if (idx == 2) {
            image_card_3.setImageResource(res)
        }
        if (idx == 3) {
            image_card_4.setImageResource(res)
        }
        if (idx == 4) {
            image_card_5.setImageResource(res)
        }
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

    override fun updateDeckSize(idx: Int) {
        train_deck_count.text = "Cards Left: $idx"
    }
}
