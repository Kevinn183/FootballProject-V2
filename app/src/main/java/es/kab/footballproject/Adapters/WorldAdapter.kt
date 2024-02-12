package es.kab.footballproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.kab.footballproject.Activities.MainActivity
import es.kab.footballproject.Classes.Nation
import es.kab.footballproject.R

class WorldAdapter(private val context: Context, private val nations:MutableList<Nation>) :RecyclerView.Adapter<WorldAdapter.TeamsViewHolder>()  {


    class TeamsViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val last_win: TextView = view.findViewById(R.id.year_world)
        private val num_tro: TextView = view.findViewById(R.id.num_tro_world)
        private val shield: ImageView = view.findViewById(R.id.shield_wolrd)
        private val win_foto: ImageView = view.findViewById(R.id.win_world)
        private val tro: ImageView = view.findViewById(R.id.world_tro)

        fun bindItem(nation: Nation, context: Context){
            last_win.text = nation.ultvict
            num_tro.text = nation.numtituls
            shield.setImageResource(nation.escu)
            win_foto.setImageResource(nation.win)
            tro.setImageResource(nation.trof)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.world_recycler, parent, false)
        return TeamsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nations.size
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        val nation = nations[position]
        holder.bindItem(nation, context)

        holder.itemView.setOnClickListener {
            val videoUrl = nation.video
            if (context is MainActivity){
                context.showVideo(videoUrl)
            }
        }
    }
}