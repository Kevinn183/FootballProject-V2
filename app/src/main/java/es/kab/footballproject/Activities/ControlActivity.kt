package es.kab.footballproject.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import es.kab.footballproject.Fragments.InicialFragment
import es.kab.footballproject.Fragments.LoginFragment
import es.kab.footballproject.Fragments.RegisterFragment
import es.kab.footballproject.R

class ControlActivity : AppCompatActivity(), InicialFragment.InicialFragmentListener, LoginFragment.LoginFragmentListener, RegisterFragment.RegisterFragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
    }

    override fun OnStartButtonClicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<LoginFragment>(R.id.controlFragmentContainer)
        }
    }

    override fun onLgnButtonCLicked() {

    }

    override fun onTextRegisterCLicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<RegisterFragment>(R.id.controlFragmentContainer)
        }
    }

    override fun onRegButtonCLicked(name: String, password: String) {
        val frag = LoginFragment()
        val bundle = Bundle().apply {
            putString("name", name)
            putString("password", password)
        }
        frag.arguments = bundle

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.controlFragmentContainer, frag)
        }
    }

    override fun <T> replace() {
    }
}