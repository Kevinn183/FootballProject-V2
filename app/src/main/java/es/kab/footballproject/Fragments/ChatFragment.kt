package es.kab.footballproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import es.kab.footballproject.Adapters.NotesAdapter
import es.kab.footballproject.Classes.Note
import es.kab.footballproject.Firebase.AuthManager
import es.kab.footballproject.Firebase.FirestoreManager
import es.kab.footballproject.R
import es.kab.footballproject.databinding.FragmentChatBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private lateinit var notes: MutableList<Note>
    private lateinit var mAdapter: NotesAdapter
    private val firestoreManager: FirestoreManager by lazy { FirestoreManager() }
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater)
        binding.enviarMsgButton.setOnClickListener {

            var contingut = binding.enviarMsgText.text.toString()
            if (!contingut.isNullOrBlank()){

                var email = AuthManager().getCurrentUser()?.email.toString()
                createNewNote(email,contingut)
                binding.enviarMsgText.setText("")
            }
            else{
                Toast.makeText(requireContext(), "don't work", Toast.LENGTH_SHORT).show()
            }
        }
        setRecyclerView()

        return binding.root
    }


    private fun setRecyclerView(){
        notes = mutableListOf()
        binding.recViewChat.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mAdapter = NotesAdapter(requireContext(), notes)
        binding.recViewChat.adapter = mAdapter

        lifecycleScope.launch (Dispatchers.IO){
            firestoreManager.getNotesFlow()
                .collect{ notesUpdated ->
                    notes.clear()
                    notes.addAll(notesUpdated)
                    withContext(Dispatchers.Main){
                        mAdapter.notifyDataSetChanged()
                    }
                }

        }
    }


    private fun createNewNote(title: String, body: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val newNote = Note(title=title, content=body)
                firestoreManager.addNote(newNote)


            } catch (e: Exception) {
                e.toString()
            }
        }
    }


}