package es.kab.footballproject.Fragments

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import es.kab.footballproject.Activities.MainActivity
import es.kab.footballproject.Adapters.NotesAdapter
import es.kab.footballproject.Classes.Note
import es.kab.footballproject.Firebase.AuthManager
import es.kab.footballproject.Firebase.FirestoreManager
import es.kab.footballproject.Firebase.PushNotificationsService
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

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var backgroundColorPreference: String

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
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
        initSharedPreferences()
        updateChatBackgroundColor()
        registerPreferenceChangeListener()

        return binding.root
    }


    private fun setRecyclerView(){
        notes = mutableListOf()
        binding.recViewChat.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mAdapter = NotesAdapter(requireContext(), notes)
        binding.recViewChat.adapter = mAdapter
        (requireActivity() as MainActivity).ToolBarName(getString(R.string.chat))

        lifecycleScope.launch (Dispatchers.IO){
            firestoreManager.getNotesFlow()
                .collect{ notesUpdated ->
                    notes.clear()
                    notes.addAll(notesUpdated)
                    withContext(Dispatchers.Main){
                        mAdapter.notifyDataSetChanged()
                        binding.recViewChat.scrollToPosition(notes.size -1)

                    }
                }

        }
    }


    private fun createNewNote(title: String, body: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val username = sharedPreferences.getString("name", "")
                if (!username.isNullOrBlank()){
                    val newNote = Note(title=username, content=body)
                    firestoreManager.addNote(newNote)
                }
                else{
                    val newNote = Note(title=title, content=body)
                    firestoreManager.addNote(newNote)
                }
                withContext(Dispatchers.Main){
                    val noti = PushNotificationsService()
                    noti.setNotification("New Message", "see your chat")
                }



            } catch (e: Exception) {
                e.toString()
            }
        }
    }

    private fun initSharedPreferences() {

        backgroundColorPreference = sharedPreferences.getString("background_color", "#FFFFFF") ?: "#FFFFFF"
    }

    private fun updateChatBackgroundColor() {
        binding.root.setBackgroundColor(Color.parseColor(backgroundColorPreference))
        sharedPreferences.edit().putString("background_color", backgroundColorPreference).apply()
    }

    private fun registerPreferenceChangeListener() {
        sharedPreferences.registerOnSharedPreferenceChangeListener { _, key ->
            if (key == "background_color") {
                backgroundColorPreference = sharedPreferences.getString("background_color", "#FFFFFF") ?: "#FFFFFF"
                updateChatBackgroundColor()
            }
        }
    }


}