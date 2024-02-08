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

    private var footballTeams = mutableListOf<FootballTeam>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamNameTextView: TextView = itemView.findViewById(R.id.teamName)
        private val amountOfPlayersTextView: TextView = itemView.findViewById(R.id.amountOfPlayers)
        private val playersTextView: TextView = itemView.findViewById(R.id.players)

        fun bind(footballTeam: FootballTeam) {
            teamNameTextView.text = footballTeam.teamName
            amountOfPlayersTextView.text = footballTeam.amountOfPlayers.toString()
            playersTextView.text = footballTeam.players.joinToString()
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

    override fun getItemCount(): Int {
        return footballTeams.size
    }

    fun updateData(newData: List<FootballTeam>) {
        footballTeams = newData.toMutableList()
        notifyDataSetChanged()
    }
}
