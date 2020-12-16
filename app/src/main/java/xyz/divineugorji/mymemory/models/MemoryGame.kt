package xyz.divineugorji.mymemory.models

import xyz.divineugorji.mymemory.utils.DEFAULT_ICONS


class MemoryGame(
        private val boardSize: BoardSize,
        private val customImages: List<String>?){

    val cards: List<MemoryCard>
    var numPairsFound = 0


    private var indexOfSingleSelectedCard: Int? = null
    private var numCardFlips = 0

    init {
        if (customImages == null){
            val chosenImages: List<Int> = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
            val randomizedImages: List<Int> = (chosenImages + chosenImages).shuffled()
            cards = randomizedImages.map { MemoryCard(it) }

        }else{
            val randomizedImages = (customImages + customImages).shuffled()
            cards = randomizedImages.map { MemoryCard(it.hashCode(), it) }
        }
    }

    fun flipCard(position: Int): Boolean {
        numCardFlips++
     val card: MemoryCard = cards[position]

        //Three cases
        //0 card previously flipped over => flip over selected card
        //1 card previously flipped over => flip over the selected card + check if the cards match
        //2 cards previously flipped over => restore card + flip over selected card
        var foundMatch = false
        if (indexOfSingleSelectedCard == null){
            restoreCard()
            indexOfSingleSelectedCard = position
        }else{
            //exactly one card flipped over
            foundMatch = checkFOrMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    private fun checkFOrMatch(position1: Int, position2: Int): Boolean {
      if (cards[position1].indentifier != cards[position2].indentifier){
          return false
      }
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }

    private fun restoreCard() {
        for (card : MemoryCard in cards){
            if(!card.isMatched){
                card.isFaceUp = false
            }
        }
    }

    fun isCardFacedUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }

    fun getNumMoves(): Int {
        return  numCardFlips / 2
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == boardSize.getNumPairs()
    }
}


















