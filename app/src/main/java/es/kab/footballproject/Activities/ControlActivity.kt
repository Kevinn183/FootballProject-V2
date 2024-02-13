package es.kab.footballproject.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import es.kab.footballproject.Firebase.AuthManager
import es.kab.footballproject.Fragments.InicialFragment
import es.kab.footballproject.Fragments.LoginFragment
import es.kab.footballproject.Fragments.PasswordFragment
import es.kab.footballproject.Fragments.RegisterFragment
import es.kab.footballproject.R

class ControlActivity : AppCompatActivity(), InicialFragment.InicialFragmentListener, LoginFragment.LoginFragmentListener, RegisterFragment.RegisterFragmentListener, PasswordFragment.PasswordListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)

        if (AuthManager().getCurrentUser() != null){
            onLgnButtonCLicked()
        }
    }

    override fun OnStartButtonClicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<LoginFragment>(R.id.controlFragmentContainer)
        }
    }

    override fun onLgnButtonCLicked() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onTextRecoverCLicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<PasswordFragment>(R.id.controlFragmentContainer)
        }
    }

    override fun onRegisterButtonCLicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<RegisterFragment>(R.id.controlFragmentContainer)
        }
    }

    override fun onRegButtonCLicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<LoginFragment>(R.id.controlFragmentContainer)
        }
    }

    override fun <T> replace() {
    }

    override fun onChaBtnClicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<LoginFragment>(R.id.controlFragmentContainer)
        }
    }
}