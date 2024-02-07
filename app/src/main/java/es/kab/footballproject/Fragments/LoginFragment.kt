package es.kab.footballproject.Fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import es.kab.footballproject.Firebase.AuthManager
import es.kab.footballproject.R
import es.kab.footballproject.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {
    private var mListener : LoginFragmentListener? = null
    private lateinit var binding: FragmentLoginBinding
    private lateinit var authManager: AuthManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        authManager = AuthManager()
        if (context is LoginFragmentListener){
            mListener = context
        }
        else{
            throw Exception("The activity must implement the interface LoginFragmentListener")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.loginBtn.setOnClickListener{
            val email = binding.usernameLog.editText?.text.toString()
            val pass = binding.passwordLog.editText?.text.toString()
            if (!email.isNullOrBlank() && ! pass.isNullOrBlank()){
                lifecycleScope.launch(Dispatchers.IO){
                    val userLogged = authManager.login(email,pass)
                    withContext(Dispatchers.Main){
                        if (userLogged != null){
                            Toast.makeText(requireContext(), userLogged.email, Toast.LENGTH_SHORT).show()
                            mListener?.onLgnButtonCLicked()
                        }else{
                            view?.let { it1 ->
                                Snackbar.make(
                                    it1,
                                    getString(R.string.nouser),
                                    Snackbar.LENGTH_SHORT
                                )
                                    .setAction(getString(R.string.create_account)) {
                                        mListener?.onTextRegisterCLicked()
                                    }.show()
                            }
                        }
                    }
                }
            }else{
                Toast.makeText(context, getString(R.string.empty), Toast.LENGTH_SHORT).show()
            }
        }
        binding.noAccount.setOnClickListener {
            mListener?.onTextRegisterCLicked()
        }

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface LoginFragmentListener{
        fun onLgnButtonCLicked()
        fun onTextRegisterCLicked()
        fun<T> replace()
    }




}