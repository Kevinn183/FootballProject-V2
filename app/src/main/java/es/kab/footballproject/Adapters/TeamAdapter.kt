package es.kab.footballproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.kab.footballproject.Classes.Team
import es.kab.footballproject.R

class TeamAdapter(private val context: Context, private val teams:MutableList<Team>) :RecyclerView.Adapter<TeamAdapter.TeamsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.team_recycler, parent, false)
        return TeamsViewHolder(view)
    }
    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        val team = teams[position]
        holder.bindItem(team, context)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    class TeamsViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val name:TextView = view.findViewById(R.id.name_team)
        private val puesto:TextView = view.findViewById(R.id.posicion_tabla)
        private val puntos:TextView = view.findViewById(R.id.num_pts)
        private val name_stadium:TextView = view.findViewById(R.id.name_stadium)
        private val num_tro:TextView = view.findViewById(R.id.num_tro)
        private val shield:ImageView = view.findViewById(R.id.shield_team)
        private val stadium:ImageView = view.findViewById(R.id.stadium_team)
        private val tro:ImageView = view.findViewById(R.id.liga_tro)

        fun bindItem(team: Team, context: Context){
            name.text = team.name
            puesto.text = team.puesto
            puntos.text = team.puntos
            name_stadium.text = team.nameStadium
            num_tro.text = team.numTrofeos
            shield.setImageResource(team.shield)
            stadium.setImageResource(team.stadium)
            tro.setImageResource(team.copa)
        }
    }
}