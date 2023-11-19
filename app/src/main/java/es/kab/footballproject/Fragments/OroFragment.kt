package es.kab.footballproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import es.kab.footballproject.Adapters.AwardAdapter
import es.kab.footballproject.Adapters.WorldAdapter
import es.kab.footballproject.Classes.Awards
import es.kab.footballproject.Classes.Nation
import es.kab.footballproject.R
import es.kab.footballproject.databinding.FragmentMundialBinding
import es.kab.footballproject.databinding.FragmentOroBinding


class OroFragment : Fragment() {

    private lateinit var binding: FragmentOroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOroBinding.inflate(inflater)
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView(){
        val awards = mutableListOf<Awards>(
            Awards("LEO MESSI",R.drawable.bandera_arg, R.drawable.messi),
            Awards("AITANA BONMATI.",R.drawable.bandera_spain, R.drawable.aitana),
            Awards("E.MARTINEZ",R.drawable.bandera_arg, R.drawable.dibu),
            Awards("E.HAALAND",R.drawable.bandera_noruega, R.drawable.erlingo),
            Awards("J.BELLINGHAM",R.drawable.bandera_ing, R.drawable.belli),
            Awards("VINICIUS JR",R.drawable.bandera_brasil, R.drawable.vini),
        )
        val awardAdapter = AwardAdapter(this.requireContext(), awards)
        binding.recViewBdo.adapter = awardAdapter
        binding.recViewBdo.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)

    }


}