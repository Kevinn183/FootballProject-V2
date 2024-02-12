package es.kab.footballproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.kab.footballproject.Classes.Team
import es.kab.footballproject.R

class TeamAdapter(private val context: Context, private var teams: List<Team>) :RecyclerView.Adapter<TeamAdapter.TeamsViewHolder>() {


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

    fun updateData(newTeams: List<Team>) {
        teams = newTeams
        notifyDataSetChanged()
    }

    class TeamsViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val name:TextView = view.findViewById(R.id.name_team)
        private val puesto:TextView = view.findViewById(R.id.posicion_tabla)
        private val puntos:TextView = view.findViewById(R.id.num_pts)
        private val win:TextView = view.findViewById(R.id.win)
        private val draw:TextView = view.findViewById(R.id.draw)
        private val loss:TextView = view.findViewById(R.id.loss)
        private val ga:TextView = view.findViewById(R.id.ga)
        private val gf:TextView = view.findViewById(R.id.gf)
        private val form:TextView = view.findViewById(R.id.form)
        private val shield:ImageView = view.findViewById(R.id.shield_team)

        fun bindItem(team: Team, context: Context){
            name.text = team.team
            puesto.text = team.pos
            puntos.text = team.points.toString()
            win.text = team.wins.toString()
            draw.text = team.draws.toString()
            loss.text = team.losses.toString()
            ga.text = team.ga
            gf.text = team.gf
            form.text = team.form
            Picasso.get().load(team.shield).into(shield)
        }
    }
}