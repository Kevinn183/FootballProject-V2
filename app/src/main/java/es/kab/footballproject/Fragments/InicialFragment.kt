package es.kab.footballproject.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import es.kab.footballproject.R


class InicialFragment : Fragment(), View.OnClickListener {

    private var mListener : InicialFragmentListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is InicialFragmentListener){
            mListener = context
        }
        else{
            throw Exception("The activity must implement the interface InicialFragmentListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inicial, container, false)
        val btnStart : Button = view.findViewById(R.id.start_button)
        btnStart.setOnClickListener(this)
        return view
    }

    interface InicialFragmentListener{
        fun OnStartButtonClicked()
        fun <T> replace()
    }
    override fun onDetach() {
        super.onDetach()
        mListener = null

    }

    override fun onClick(v: View?) {
        if (v != null){
            when(v.id){
                R.id.start_button -> mListener?.OnStartButtonClicked()
            }
        }
    }


}