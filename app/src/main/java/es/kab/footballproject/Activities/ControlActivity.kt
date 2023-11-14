package es.kab.footballproject.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import es.kab.footballproject.Fragments.InicialFragment
import es.kab.footballproject.Fragments.LoginFragment
import es.kab.footballproject.Fragments.RegisterFragment
import es.kab.footballproject.R

class ControlActivity : AppCompatActivity(), InicialFragment.InicialFragmentListener {
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

    override fun <T> replace() {
        TODO("Not yet implemented")
    }
}