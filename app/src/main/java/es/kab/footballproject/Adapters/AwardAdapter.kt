package es.kab.footballproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.kab.footballproject.Classes.Awards
import es.kab.footballproject.Classes.Nation
import es.kab.footballproject.Classes.Team
import es.kab.footballproject.R

class AwardAdapter(private val context: Context, private val awards:MutableList<Awards>) :RecyclerView.Adapter<AwardAdapter.AwardsViewHolder>() {


    class AwardsViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val name: TextView = view.findViewById(R.id.name_player)
        private val flag: ImageView = view.findViewById(R.id.flag)
        private val player: ImageView = view.findViewById(R.id.player)

        fun bindItem(award: Awards, context: Context){
            name.text = award.name
            flag.setImageResource(award.flag)
            player.setImageResource(award.player)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AwardsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.bdo_recycler, parent, false)
        return AwardsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return awards.size
    }

    override fun onBindViewHolder(holder: AwardsViewHolder, position: Int) {
        val award = awards[position]
        holder.bindItem(award, context)
    }
}