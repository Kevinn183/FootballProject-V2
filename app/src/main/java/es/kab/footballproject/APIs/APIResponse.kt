package es.kab.footballproject.APIs

import com.google.gson.annotations.SerializedName
import es.kab.footballproject.Classes.Team

data class APIResponse(
    @SerializedName("table") val teams: List<Team>
)

