package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.BoardService
import com.d.tickettoride.views.iviews.IGameView

class GamePresenter(private val gameActivity: IGameView,
                    private val boardService: BoardService = BoardService.instance): IGamePresenter {

    init {
        var rootModel = RootModel.instance
        rootModel.onDestinationCardsGiven = { _, new ->
            gameActivity.displayDestPickPopup()
        }
    }

    override fun getBoard(): Board {
        return boardService.getBoard()
    }
}