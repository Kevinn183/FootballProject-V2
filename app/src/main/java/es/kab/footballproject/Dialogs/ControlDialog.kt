package es.kab.footballproject.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import es.kab.footballproject.R
import java.lang.IllegalStateException

class ControlDialog : DialogFragment() {

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        fun newInstance(name: String, pass: String): ControlDialog {
            val dialControl = ControlDialog()
            val args = Bundle()
            args.putString(ARG_PARAM1, name)
            args.putString(ARG_PARAM2, pass)
            dialControl.arguments = args
            return dialControl
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = layoutInflater.inflate(R.layout.fragment_dialog, null)
        val nom : TextView = view.findViewById(R.id.un)
        val pas : TextView = view.findViewById(R.id.up)
        val param1 = arguments?.getString(ARG_PARAM1)
        val param2 = arguments?.getString(ARG_PARAM2)
        nom.text = "Username: $param1"
        pas.text = "Password: $param2"
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setView(view)
                //.setPositiveButton("ok"){dialog, id -> }
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")

    }
}