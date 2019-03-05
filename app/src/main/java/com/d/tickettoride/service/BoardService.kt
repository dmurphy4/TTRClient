package com.d.tickettoride.service

import com.d.tickettoride.model.gameplay.Board
import com.google.gson.Gson
import java.io.File

class BoardService {

    companion object {
        val instance = BoardService()
    }

    fun claimRoute() {

    }

    fun getBoard(): Board {
        val bufferedReader = File("../util/board.json").bufferedReader()
        val boardString = bufferedReader.use { it.readText() }

        return Gson().fromJson(boardString, Board::class.java)
    }
}