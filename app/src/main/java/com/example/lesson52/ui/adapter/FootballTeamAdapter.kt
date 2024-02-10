package com.example.lesson52.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson52.R
import com.example.lesson52.data.local.room.entities.FootballTeam

class FootballTeamAdapter :
    RecyclerView.Adapter<FootballTeamAdapter.ViewHolder>() {

    var footballTeams = mutableListOf<FootballTeam>()
    private var onItemLongClickListener: OnItemLongClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamNameTextView: TextView = itemView.findViewById(R.id.teamName)
        private val amountOfPlayersTextView: TextView = itemView.findViewById(R.id.amountOfPlayers)
        private val playersTextView: TextView = itemView.findViewById(R.id.players)

        fun bind(footballTeam: FootballTeam) {
            teamNameTextView.text = footballTeam.teamName
            amountOfPlayersTextView.text = footballTeam.amountOfPlayers.toString()
            playersTextView.text = footballTeam.players.joinToString()
            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemLongClickListener?.onItemLongClick(position)
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val footballTeam = footballTeams[position]
        holder.bind(footballTeam)
    }

    override fun getItemCount() = footballTeams.size

    fun updateData(newData: List<FootballTeam>) {
        footballTeams.clear()
        footballTeams.addAll(newData)
        notifyDataSetChanged()
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.onItemLongClickListener = listener
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }
}
