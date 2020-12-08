package xyz.divineugorji.mymemory

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import xyz.divineugorji.mymemory.models.BoardSize
import xyz.divineugorji.mymemory.models.MemoryCard
import kotlin.math.min

class MemoryBoardAdapter(
        private val context: Context,
        private val boardSize: BoardSize,
        private val cards: List<MemoryCard>) :
        RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder>() {

    companion object{
        private const val MARGIN_SIZE = 10
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardWith: Int = parent.width / boardSize.getWidth() - (2* MARGIN_SIZE)
        val cardHeight: Int = parent.height / boardSize.getHeight() - (2* MARGIN_SIZE)
        val cardSIdeLength: Int = min(cardWith, cardHeight)
        val view =  LayoutInflater.from(context).inflate(R.layout.memory_card, parent, false)
        val layoutParams : ViewGroup.MarginLayoutParams = view.findViewById<CardView>(R.id.card_view).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width = cardSIdeLength
        layoutParams.height = cardSIdeLength
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)
        return ViewHolder(view)
    }

    override fun getItemCount() = boardSize.numCards

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(position)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)

        fun bind(position: Int) {
            val memoryCard: MemoryCard = cards[position]
           imageButton.setImageResource(if (cards[position].isFaceUp) memoryCard.indentifier else R.drawable.ic_launcher_background)
            imageButton.setOnClickListener {
                Log.i(TAG, "clicked on position $position")
            }
        }
    }
}
