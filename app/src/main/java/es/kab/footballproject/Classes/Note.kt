package es.kab.footballproject.Classes

import java.util.Date

data class Note(
    var id: String? = null,
    var userId: String = "",
    var title: String = "",
    var content: String = "",
    var date: Date? = null
)
