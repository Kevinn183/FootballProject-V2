package es.kab.footballproject.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import es.kab.footballproject.R
import es.kab.footballproject.databinding.DialogInputBinding
import java.lang.IllegalStateException

class DialogInput : DialogFragment() {

    private lateinit var binding : DialogInputBinding
    private val editText: EditText = binding.editTextDialogInput

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setView(binding.root)
                .setPositiveButton("Aceptar") { _, _ ->
                    val userInput = editText.text.toString()
                    //onPositiveButtonClick.invoke(userInput)
                }
                .setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                })
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")

    }
}