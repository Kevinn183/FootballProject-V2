package es.kab.footballproject.Fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import es.kab.footballproject.R
import es.kab.footballproject.databinding.FragmentMakeBinding
import es.kab.footballproject.databinding.FragmentOroBinding

class MakeFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMakeBinding

//    private var player1: TextView? = null
//    private var player2: TextView? = null
//    private var player3: TextView? = null
//    private var player4: TextView? = null
//    private var player5: TextView? = null
//    private var player6: TextView? = null
//    private var player7: TextView? = null
//    private var player8: TextView? = null
//    private var player9: TextView? = null
//    private var player10: TextView? = null
//    private var player11: EditText? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMakeBinding.inflate(inflater)
        binding.floatingCaptura.setOnClickListener(this)

        return binding.root
         }

    override fun onClick(v: View?) {

        if (v!=null){
            when(v.id){
                R.id.floatingCaptura ->{
                    Toast.makeText(context, "Screenshot", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }



}