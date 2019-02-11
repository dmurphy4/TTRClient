package com.d.tickettoride.model

class GameListWrapper<GameInfo> : AbstractList<GameInfo>() {

    private var delegate = ArrayList<GameInfo>()

    override var size = 0

    override fun get(ind:Int) : GameInfo {
        return delegate[ind]
    }

    fun add(gameInfo:GameInfo) {
        delegate.add(gameInfo)
        size++
    }

    fun removeAt(ind:Int) {
        delegate.removeAt(ind)
        size--
    }

    fun remove(gameInfo:GameInfo) {
        delegate.remove(gameInfo)
        size--
    }
}