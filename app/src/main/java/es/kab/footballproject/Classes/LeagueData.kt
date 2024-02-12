package es.kab.footballproject.Classes

data class LeagueData(
    val leagueName: String,
    val year: Int,
    val teams: List<Team>
)
