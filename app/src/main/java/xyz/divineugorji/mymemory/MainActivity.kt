package xyz.divineugorji.mymemory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import xyz.divineugorji.mymemory.models.BoardSize
import xyz.divineugorji.mymemory.models.MemoryCard
import xyz.divineugorji.mymemory.utils.DEFAULT_ICONS

class MainActivity : AppCompatActivity() {

    private var boardSize: BoardSize = BoardSize.HARD

    private lateinit var rvBoard: RecyclerView
    private lateinit var tvNumMoves: TextView
    private lateinit var tvNumPairs: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rvBoard = findViewById(R.id.rvBoard)
        tvNumMoves = findViewById(R.id.tvNumMoves)
        tvNumPairs = findViewById(R.id.tvNumPairs)

        val chosenImages: List<Int> = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages: List<Int> = (chosenImages + chosenImages).shuffled()
        val memoryCards: List<MemoryCard> = randomizedImages.map { MemoryCard(it) }


        rvBoard.adapter = MemoryBoardAdapter(this, boardSize, memoryCards)
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())

    }
}