package es.kab.footballproject.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import retrofit2.Callback
import androidx.recyclerview.widget.LinearLayoutManager
import es.kab.footballproject.APIs.APIResponse
import es.kab.footballproject.APIs.LeagueService
import es.kab.footballproject.APIs.RetrofitObject
import es.kab.footballproject.Activities.MainActivity
import es.kab.footballproject.Adapters.TeamAdapter
import es.kab.footballproject.Classes.Team
import es.kab.footballproject.R
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

        // Inicializar el adaptador del RecyclerView
        teamAdapter = TeamAdapter(requireContext(), emptyList())
        binding.recView.adapter = teamAdapter
        binding.recView.layoutManager = LinearLayoutManager(requireContext())

        // Llamar a la función para obtener los datos de la API
        fetchTeamsData()

        return binding.root
    }

    private fun fetchTeamsData() {
        // Crear una instancia del servicio de la API utilizando RetrofitObject
        val leagueService = RetrofitObject.getInstance().create(LeagueService::class.java)

        // Hacer la llamada a la API para obtener los datos de los equipos de fútbol
        val call = leagueService.getLeagueTable()
        call.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    // Procesar la respuesta exitosa aquí
                    apiResponse?.let { handleApiResponse(it) }
                } else {
                    // Manejar respuesta no exitosa aquí
                    Log.e(TAG, "Error en la respuesta: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                // Manejar error de red aquí
                Log.e(TAG, "Error en la llamada: ${t.message}")
            }
        })
    }

    private fun handleApiResponse(apiResponse: APIResponse) {
        // Obtener la lista de equipos de la respuesta
        val teams = apiResponse.teams

        // Actualizar el adaptador del RecyclerView con los datos de los equipos
        teamAdapter.updateData(teams)
    }

    companion object {
        private const val TAG = "TeamFragment"
    }
}
