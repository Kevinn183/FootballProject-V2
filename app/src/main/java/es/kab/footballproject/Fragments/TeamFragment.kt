package es.kab.footballproject.Fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import retrofit2.Callback
import androidx.recyclerview.widget.LinearLayoutManager
import es.kab.footballproject.APIs.APIResponse
import es.kab.footballproject.APIs.LeagueService
import es.kab.footballproject.APIs.RetrofitObject
import es.kab.footballproject.Activities.MainActivity
import es.kab.footballproject.Adapters.TeamAdapter
import es.kab.footballproject.Classes.Team
import es.kab.footballproject.Classes.Teambase
import es.kab.footballproject.DAO.AppDB
import es.kab.footballproject.databinding.FragmentTeamBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding
    private lateinit var teamAdapter: TeamAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamBinding.inflate(inflater)
        (requireActivity() as MainActivity).ToolBarName("LA LIGA")
        teamAdapter = TeamAdapter(requireContext(), emptyList())
        binding.recView.adapter = teamAdapter
        binding.recView.layoutManager = LinearLayoutManager(requireContext())

        val isConnected = isNetworkConnected(requireContext())
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val switchValue = sharedPreferences.getBoolean("offline", false)

        if (!isConnected && switchValue){
            getTeamsDB()
        }
        else{
            SearchTeams()
        }


        return binding.root
    }

    private fun SearchTeams() {
        val leagueService = RetrofitObject.getInstance().create(LeagueService::class.java)
        val call = leagueService.getLeagueTable()
        call.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    apiResponse?.let { handleApiResponse(it) }
                } else {
                    Log.e(TAG, "Error en la respuesta: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                Log.e(TAG, "Error en la respuesta")
            }


        })
    }

    private fun handleApiResponse(apiResponse: APIResponse) {
        val teams = apiResponse.teams


        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val switchValue = sharedPreferences.getBoolean("offline", false)
        if (switchValue){
            lifecycleScope.launch {
                teams.forEach {
                    var newt = Teambase(team = it.team, pos = it.pos, points = it.points, shield = it.shield, wins = it.wins, draws = it.draws, losses = it.losses, form = it.form, gf = it.gf, ga = it.ga)
                    AppDB.getInstance(requireContext()).teamsDAO().insert(newt)
                }
            }
        }
        teamAdapter.updateData(teams)
    }


    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    fun getTeamsDB(){
        lifecycleScope.launch {
            val allTeams = AppDB.getInstance(requireContext()).teamsDAO().getAll()
            launch(Dispatchers.Main){
                var teamsaux: MutableList<Team> = mutableListOf()
                allTeams.forEach{
                    var team = Team(team = it.team, pos = it.pos, points = it.points, shield = it.shield, wins = it.wins, draws = it.draws, losses = it.losses, form = it.form, gf = it.gf, ga = it.ga)
                    teamsaux.add(team)
                }
                val teams :List<Team> = teamsaux
                teamAdapter.updateData(teams)
            }
        }

    }



    companion object {
        private const val TAG = "TeamFragment"
    }
}
