package xyz.divineugorji.mymemory.models

data class MemoryCard(
        val indentifier: Int,
        val imageUrl: String? = null,
        var isFaceUp: Boolean = false,
        var isMatched: Boolean = false
)