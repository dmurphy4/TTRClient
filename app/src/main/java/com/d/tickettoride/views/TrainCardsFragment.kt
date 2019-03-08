package com.d.tickettoride.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.d.tickettoride.R
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
            image_card_2.setImageResource(R.drawable.cardblack)
            image_card_3.setImageResource(R.drawable.cardlocomotive)
            image_card_3.setOnClickListener {
                image_card_3.setImageResource(R.drawable.cardyellow)
            }
            image_card_4.setImageResource(R.drawable.cardgreen)
            image_card_5.setImageResource(R.drawable.cardred)
            train_deck_count.text = "105"
        }

        return view
    }


}
