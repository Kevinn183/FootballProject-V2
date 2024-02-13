package es.kab.footballproject.APIs

import retrofit2.http.GET
import retrofit2.http.Query

interface LeagueService {
    @GET("scripts/api/api.php?key=5393dd3c92db5dfe46eec23688e6ecd0&format=json&req=tables&league=1&year=2024")
    fun getLeagueTable(): retrofit2.Call<APIResponse>
}