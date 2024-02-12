package es.kab.footballproject.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.Callback
import androidx.recyclerview.widget.LinearLayoutManager
import es.kab.footballproject.APIs.APIResponse
import es.kab.footballproject.APIs.LeagueService
import es.kab.footballproject.APIs.RetrofitObject
import es.kab.footballproject.Activities.MainActivity
import es.kab.footballproject.Adapters.TeamAdapter
import es.kab.footballproject.databinding.FragmentTeamBinding
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
        fetchTeamsData()

        return binding.root
    }

    private fun fetchTeamsData() {
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
                TODO("Not yet implemented")
            }

        })
    }

    private fun handleApiResponse(apiResponse: APIResponse) {
        val teams = apiResponse.teams
        teamAdapter.updateData(teams)
    }

    companion object {
        private const val TAG = "TeamFragment"
    }
}
