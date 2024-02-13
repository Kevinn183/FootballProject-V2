package es.kab.footballproject.Firebase

import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import es.kab.footballproject.Classes.Note
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


class FirestoreManager {

    private val firestore by lazy { FirebaseFirestore.getInstance() }
    suspend fun addNote(note: Note): Boolean {

        return try {
            note.date = Timestamp.now()
            firestore.collection("mensajes").add(note).await()
            true
        }catch(e: Exception){
            false
        }
    }

    suspend fun getNotesFlow(): Flow<List<Note>> = callbackFlow{
        var notesCollection: CollectionReference? = null
        try {
            notesCollection = FirebaseFirestore.getInstance()
                .collection("mensajes")
            val subscription = notesCollection.orderBy("date",Query.Direction.ASCENDING).addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    val notes = mutableListOf<Note>()
                    snapshot.forEach{
                        notes.add(
                            Note(
                                id = it.get("id").toString(),
                                title = it.get("title").toString(),
                                content = it.get("content").toString(),
                                date = Timestamp.now()
                            )
                        )
                    }
                    trySend(notes)
                }
            }
            awaitClose { subscription?.remove() }

        } catch (e: Throwable) {

            close(e)
        }
    }

}