package es.kab.footballproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import es.kab.footballproject.Activities.MainActivity
import es.kab.footballproject.Adapters.WorldAdapter
import es.kab.footballproject.Classes.Nation
import es.kab.footballproject.R
import es.kab.footballproject.databinding.FragmentMundialBinding

class WorldFragment : Fragment() {

    private lateinit var binding: FragmentMundialBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMundialBinding.inflate(inflater)
        (requireActivity() as MainActivity).ToolBarName(getString(R.string.mundial))
        setUpRecyclerView()
        return binding.root
    }


    private fun setUpRecyclerView(){
        val nations = mutableListOf<Nation>(
            Nation("7", R.drawable.brasil, R.drawable.win_brasil,R.drawable.mundial, "2002","brasil"),
            Nation("4", R.drawable.italia, R.drawable.win_italy,R.drawable.mundial, "2006","italia"),
            Nation("4", R.drawable.alemania, R.drawable.win_alemania,R.drawable.mundial, "2014","alemania"),
            Nation("3", R.drawable.argentina2, R.drawable.win_argentina,R.drawable.mundial, "2022","argentina"),
            Nation("2", R.drawable.uruguay, R.drawable.win_uruguay,R.drawable.mundial, "1950","uruguay"),
            Nation("2", R.drawable.francia, R.drawable.win_francia,R.drawable.mundial, "2018","francia"),
            Nation("1", R.drawable.espana, R.drawable.win_spain,R.drawable.mundial, "2010","espanya"),
            Nation("7", R.drawable.inglaterra, R.drawable.win_england,R.drawable.mundial, "1966","inglaterra"),
            )
        val worldAdapter = WorldAdapter(this.requireContext(), nations)
        binding.recViewWorld.adapter = worldAdapter
        binding.recViewWorld.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)

    }
}

