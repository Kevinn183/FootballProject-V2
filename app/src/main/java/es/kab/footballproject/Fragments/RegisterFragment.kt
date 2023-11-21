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
import com.google.android.material.textfield.TextInputLayout
import es.kab.footballproject.Dialogs.ControlDialog
import es.kab.footballproject.R


class RegisterFragment : Fragment(), View.OnClickListener {
    private var nom: TextInputLayout? = null
    private var passw: TextInputLayout? = null
    private var reppassw: TextInputLayout? = null

    private var mListener : RegisterFragmentListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val btnReg : Button = view.findViewById(R.id.register_btn)
        nom = view.findViewById(R.id.reg_name)
        passw = view.findViewById(R.id.reg_pass)
        reppassw = view.findViewById(R.id.reg_pass_confirm)
        btnReg.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        if (v != null){
            when(v.id){
                R.id.register_btn ->{
                    if (nom?.editText?.text.toString().trim().isEmpty()|| passw?.editText?.text.toString().trim().isEmpty() || reppassw?.editText?.text.toString().trim().isEmpty()){
                        Toast.makeText(context, getString(R.string.empty), Toast.LENGTH_SHORT).show()
                    }
                    else if (passw?.editText?.text.toString() != reppassw?.editText?.text.toString()){
                        Toast.makeText(context, getString(R.string.nopass), Toast.LENGTH_SHORT).show()
                    }
                    else{
                        val dialogFragment = ControlDialog.newInstance(nom?.editText?.text.toString(),passw?.editText?.text.toString())
                        dialogFragment.show(requireActivity().supportFragmentManager, "LOGIN")
                        mListener?.onRegButtonCLicked(nom?.editText?.text.toString(), passw?.editText?.text.toString())
                    }
                }
            }
        }
    }

    interface RegisterFragmentListener{
        fun onRegButtonCLicked(name : String, password : String)
        fun<T> replace()
    }




}