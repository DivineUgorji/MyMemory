package xyz.divineugorji.mymemory.models

data class MemoryCard(
        val indentifier: Int,
        var isFaceUp: Boolean = false,
        var isMatched: Boolean = false
)