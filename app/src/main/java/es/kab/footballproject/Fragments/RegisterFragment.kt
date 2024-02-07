package es.kab.footballproject.Fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import es.kab.footballproject.Dialogs.ControlDialog
import es.kab.footballproject.Firebase.AuthManager
import es.kab.footballproject.R
import es.kab.footballproject.databinding.FragmentRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private var mListener : RegisterFragmentListener? = null
    private lateinit var authManager:AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        authManager = AuthManager()
        if (context is RegisterFragmentListener){
            mListener = context
        }
        else{
            throw Exception("The activity must implement the interface RegisterFragmentListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        binding.registerBtn.setOnClickListener {
            val email = binding.regName.editText?.text.toString()
            val pass = binding.regPass.editText?.text.toString()
            val repass = binding.regPassConfirm.editText?.text.toString()
            if (!email.isNullOrBlank() && ! pass.isNullOrBlank()){
                if (pass != repass){
                    Toast.makeText(context, getString(R.string.nopass), Toast.LENGTH_SHORT).show()
                }
                else{
                    lifecycleScope.launch(Dispatchers.IO){
                        val userLogged = authManager.singin(email,pass)
                        withContext(Dispatchers.Main){
                            if (userLogged != null){
                                Toast.makeText(requireContext(), userLogged.email, Toast.LENGTH_SHORT).show()
                                val dialogFragment = ControlDialog.newInstance(email,pass)
                                dialogFragment.show(requireActivity().supportFragmentManager, "LOGIN")
                                mListener?.onRegButtonCLicked()
                            }else{
                                Toast.makeText(requireContext(),"Bad credentials", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

            }
        }


        return binding.root
    }



    interface RegisterFragmentListener{
        fun onRegButtonCLicked()
        fun<T> replace()
    }




}