package es.kab.footballproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import es.kab.footballproject.Adapters.TeamAdapter
import es.kab.footballproject.Classes.Team
import es.kab.footballproject.R
import es.kab.footballproject.databinding.FragmentTeamBinding

class TeamFragment : Fragment() {

    private lateinit var binding:FragmentTeamBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamBinding.inflate(inflater)
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView(){
        val teams = mutableListOf<Team>(
            Team("GIRONA", "1º","34","Montilivi", "0", R.drawable.girona, R.drawable.montilivi, R.drawable.trofeoliga),
            Team("R.MADRID", "2º","32","S.Bernabeu", "35", R.drawable.realmadrid, R.drawable.bernabeu, R.drawable.trofeoliga),
            Team("BARCELONA", "3º","30","Camp Nou", "27", R.drawable.barcelona, R.drawable.campnou, R.drawable.trofeoliga),
            Team("A.MADRID", "4º","28","C.Metropolitano", "11", R.drawable.atlmadrid, R.drawable.civitas, R.drawable.trofeoliga),
            Team("ATHLETIC C.", "5º","24","San Mames", "8", R.drawable.athletic, R.drawable.sanmames, R.drawable.trofeoliga),
            Team("REAL SOC.", "6º","22","Reale Arena", "2", R.drawable.realsociedad, R.drawable.anoeta, R.drawable.trofeoliga),
            Team("R.BETIS", "7º","21","B.Villamarin", "1", R.drawable.betis, R.drawable.villamarin, R.drawable.trofeoliga),
            Team("PALMAS", "8º","18","Gran Canaria", "0", R.drawable.udlaspalmas, R.drawable.grancanaria, R.drawable.trofeoliga),
            Team("VALENCIA", "9º","18","Mestalla", "6", R.drawable.valencia, R.drawable.mestalla, R.drawable.trofeoliga),
            Team("RAYO V.", "10º","18","Vallecas", "0", R.drawable.rayovallecano, R.drawable.vallecas, R.drawable.trofeoliga),
            Team("GETAFE", "11º","16","Coliseum", "0", R.drawable.getafe, R.drawable.coliseum, R.drawable.trofeoliga),
            Team("OSASUNA", "12º","14","El Sadar", "0", R.drawable.osasuna, R.drawable.sadar, R.drawable.trofeoliga),
            Team("SEVILLA", "13º","12","S.Pizjuan", "1", R.drawable.sevilla, R.drawable.pizjuan, R.drawable.trofeoliga),
            Team("VILLAREAL", "14º","12","La Ceramica", "0", R.drawable.villarreal, R.drawable.ceramica, R.drawable.trofeoliga),
            Team("ALAVES", "15º","12","Mendizorroza", "0", R.drawable.alaves, R.drawable.mendizorroza, R.drawable.trofeoliga),
            Team("CADIZ", "16º","10","N.Mirandilla", "0", R.drawable.cadiz, R.drawable.carranza, R.drawable.trofeoliga),
            Team("MALLORCA", "17º","9","Son Moix", "0", R.drawable.mallorca, R.drawable.sonmoix, R.drawable.trofeoliga),
            Team("CELTA", "18º","7","Balaidos", "0", R.drawable.celta, R.drawable.balaidos, R.drawable.trofeoliga),
            Team("GRANADA", "19º","7","Los Carmenes", "0", R.drawable.granada, R.drawable.carmenes, R.drawable.trofeoliga),
            Team("ALMERIA", "20º","3","J.Mediterraneo", "0", R.drawable.almeria, R.drawable.juegosmediterraneo, R.drawable.trofeoliga),

        )
        val teamAdapter = TeamAdapter(this.requireContext(), teams)
        binding.recView.adapter = teamAdapter
        binding.recView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)

    }
}