package es.kab.footballproject.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import es.kab.footballproject.Firebase.AuthManager
import es.kab.footballproject.databinding.FragmentPasswordBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PasswordFragment : Fragment() {


    private lateinit var binding: FragmentPasswordBinding
    private lateinit var mListener: PasswordListener
    private lateinit var authManager: AuthManager


    override fun onAttach(context: Context) {
        super.onAttach(context)

        authManager = AuthManager()
        if(context is PasswordListener){
            mListener = context
        }else{
            throw Exception("Your fragment or activity must implement the interface ResetPasswordListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordBinding.inflate(inflater,container,false)

        binding.buttonCHANGE.setOnClickListener {
            val email = binding.userText.editText?.text.toString()

            if(!email.isNullOrBlank()){
                lifecycleScope.launch(Dispatchers.IO){
                    authManager.changepass(email)
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(), "See your email", Toast.LENGTH_SHORT).show()
                        mListener.onChaBtnClicked()
                    }
                }
            }
        }
        return binding.root
    }

    interface PasswordListener{
        fun onChaBtnClicked()
    }


}