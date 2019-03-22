package com.d.tickettoride.model.gameplay

class TrainCarCardHand(val cards:List<TrainCarCard>) : IHand {

    private lateinit var cardMap: HashMap<TrainCarCardType, Int>

    fun setUpMap() {
        cardMap = hashMapOf(TrainCarCardType.RED to 0,
        TrainCarCardType.BLACK to 0,
        TrainCarCardType.BLUE to 0,
        TrainCarCardType.GREEN to 0,
        TrainCarCardType.YELLOW to 0,
        TrainCarCardType.ORANGE to 0,
        TrainCarCardType.WHITE to 0,
        TrainCarCardType.PURPLE to 0,
        TrainCarCardType.LOCOMOTIVE to 0
        )

        for(card in cards) {
            cardMap[card.type] = cardMap[card.type]!! + 1
        }
    }

    var onHandChanged: ((TrainCarCardHand) -> Unit)? = null

    init {

    }

    fun changeCardCount(type: TrainCarCardType, amount: Int) {
        cardMap[type]!!.plus(amount)
        onHandChanged?.invoke(this)
    }

    fun getAmountOfType(type: TrainCarCardType): Int {
        return when (type) {
            TrainCarCardType.RED -> getRed()
            TrainCarCardType.BLACK -> getBlack()
            TrainCarCardType.BLUE -> getBlue()
            TrainCarCardType.GREEN -> getGreen()
            TrainCarCardType.YELLOW -> getYellow()
            TrainCarCardType.ORANGE -> getOrange()
            TrainCarCardType.WHITE -> getWhite()
            TrainCarCardType.PURPLE -> getPurple()
            TrainCarCardType.LOCOMOTIVE -> getLocomotive()
            else -> -1
        }
    }

    fun getRed(): Int {
        return cardMap[TrainCarCardType.RED]!!
    }

    fun getBlack(): Int {
        return cardMap[TrainCarCardType.BLACK]!!
    }

    fun getBlue(): Int {
        return cardMap[TrainCarCardType.BLUE]!!
    }

    fun getGreen(): Int {
        return cardMap[TrainCarCardType.GREEN]!!
    }

    fun getYellow(): Int {
        return cardMap[TrainCarCardType.YELLOW]!!
    }

    fun getOrange(): Int {
        return cardMap[TrainCarCardType.ORANGE]!!
    }

    fun getWhite(): Int {
        return cardMap[TrainCarCardType.WHITE]!!
    }

    fun getPurple(): Int {
        return cardMap[TrainCarCardType.PURPLE]!!
    }

    fun getLocomotive(): Int {
        return cardMap[TrainCarCardType.LOCOMOTIVE]!!
    }

    override fun draw(cardsDrawn: List<ICard>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playCards() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}