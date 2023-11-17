package es.kab.footballproject.Fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import es.kab.footballproject.R


class LoginFragment : Fragment(), View.OnClickListener {
    private var mListener : LoginFragmentListener? = null



    private var user: TextInputLayout? = null
    private var pass: TextInputLayout? = null
    private var name: String? = null
    private var password: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val btnLog : Button = view.findViewById(R.id.login_btn)
        val regtext : TextView = view.findViewById(R.id.no_account)
        user = view.findViewById(R.id.username_log)
        pass = view.findViewById(R.id.password_log)
        name = arguments?.getString("name")
        password = arguments?.getString("password")
        btnLog.setOnClickListener(this)
        regtext.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        if (v != null){
            when(v.id){
                R.id.login_btn ->{
                    if ( user?.editText?.text.toString().trim().isEmpty()|| pass?.editText?.text.toString().trim().isEmpty()){
                        Toast.makeText(context, "there can't be empty fields", Toast.LENGTH_SHORT).show()
                    }
                    else if (user?.editText?.text.toString() == name && pass?.editText?.text.toString() == password){
                        mListener?.onLgnButtonCLicked()
                    }
                    else{
                        Snackbar.make(v, "User not found",Snackbar.LENGTH_SHORT).setAction("REGISTER NOW"){
                            mListener?.onTextRegisterCLicked()
                        }.show()
                    }
                }
                R.id.no_account ->{
                    mListener?.onTextRegisterCLicked()
                }
            }
        }
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