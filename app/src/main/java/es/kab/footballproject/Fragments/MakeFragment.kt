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

    private var player1: TextView? = null
    private var player2: TextView? = null
    private var player3: TextView? = null
    private var player4: TextView? = null
    private var player5: TextView? = null
    private var player6: TextView? = null
    private var player7: TextView? = null
    private var player8: TextView? = null
    private var player9: TextView? = null
    private var player10: TextView? = null
    private var player11: TextView? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMakeBinding.inflate(inflater)

        player1 = binding.player1
        player2 = binding.player2
        player3 = binding.player3
        player4 = binding.player4
        player5 = binding.player5
        player6 = binding.player6
        player7 = binding.player7
        player8 = binding.player8
        player9 = binding.player9
        player10 = binding.player10
        player11= binding.player11

        val cam1 = binding.cam1
        val cam2 = binding.cam2
        val cam3 = binding.cam3
        val cam4 = binding.cam4
        val cam5 = binding.cam5
        val cam6 = binding.cam6
        val cam7 = binding.cam7
        val cam8 = binding.cam8
        val cam9 = binding.cam9
        val cam10 = binding.cam10
        val cam11 = binding.cam11

        cam1.setOnClickListener(this)
        cam2.setOnClickListener(this)
        cam3.setOnClickListener(this)
        cam4.setOnClickListener(this)
        cam5.setOnClickListener(this)
        cam6.setOnClickListener(this)
        cam7.setOnClickListener(this)
        cam8.setOnClickListener(this)
        cam9.setOnClickListener(this)
        cam10.setOnClickListener(this)
        cam11.setOnClickListener(this)

        return binding.root
         }

    override fun onClick(v: View?) {
//        val inflater = LayoutInflater.from(requireContext())
//        val dialogView = inflater.inflate(R.layout.dialog_input, null)
//        val editText : EditText= dialogView.findViewById(R.id.editTextDialogInput)
//
//        val alertDialogBuilder = AlertDialog.Builder(requireContext())
//        alertDialogBuilder.setView(dialogView)

//        val inflater = LayoutInflater.from(requireContext())
//        val dialogView = inflater.inflate(R.layout.dialog_input, null)
//        val editText = dialogView.findViewById<EditText>(R.id.editTextDialogInput)
//
//        val alertDialogBuilder = AlertDialog.Builder(requireContext())
//        alertDialogBuilder.setView(dialogView)

        if (v!=null){
            when(v.id){
                R.id.cam1 ->{
                    Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
                }
                R.id.cam2 ->{
                    Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
                }
                R.id.cam3 ->{
                    Toast.makeText(context, "3", Toast.LENGTH_SHORT).show()
                }
                R.id.cam4 ->{
                    Toast.makeText(context, "4", Toast.LENGTH_SHORT).show()
                }
                R.id.cam5 ->{
                    Toast.makeText(context, "5", Toast.LENGTH_SHORT).show()
                }
                R.id.cam6 ->{
                    Toast.makeText(context, "6", Toast.LENGTH_SHORT).show()
                }
                R.id.cam7 ->{
                    Toast.makeText(context, "7", Toast.LENGTH_SHORT).show()
                }
                R.id.cam8 ->{
                    Toast.makeText(context, "8", Toast.LENGTH_SHORT).show()
                }
                R.id.cam9 ->{
                    Toast.makeText(context, "9", Toast.LENGTH_SHORT).show()
                }
                R.id.cam10 ->{
                    Toast.makeText(context, "10", Toast.LENGTH_SHORT).show()
                }
                R.id.cam11 ->{
                    Toast.makeText(context, "11", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }



}