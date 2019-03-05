package com.d.tickettoride.model.gameplay

interface IDeck {
    fun draw() : List<ICard>
}