package es.kab.footballproject.Classes

import com.google.firebase.Timestamp
import java.util.Date

data class Note(
    var id: String? = null,
    var title: String = "",
    var content: String = "",
    var date: Timestamp? = null
)
