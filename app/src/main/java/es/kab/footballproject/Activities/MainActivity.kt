package es.kab.footballproject.Activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.preference.PreferenceManager
import com.google.android.material.navigation.NavigationBarView
import es.kab.footballproject.Firebase.AuthManager
import es.kab.footballproject.Fragments.ChatFragment
import es.kab.footballproject.Fragments.MakeFragment
import es.kab.footballproject.Fragments.MultimediaFragment
import es.kab.footballproject.Fragments.WorldFragment
import es.kab.footballproject.Fragments.OroFragment
import es.kab.footballproject.Fragments.TeamFragment
import es.kab.footballproject.R
import es.kab.footballproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding : ActivityMainBinding
    companion object{
        private val NOTIFICATIONS_REQUEST_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNav.setOnItemSelectedListener(this)
        setSupportActionBar(binding.myToolbar)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                NOTIFICATIONS_REQUEST_CODE
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.actionLogOut ->{
                AuthManager().logOut()
                val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
                sharedPreferences.edit().clear().apply()
                Toast.makeText(this,getString(R.string.exit),Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ControlActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.actionSettings->{
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onNavigationItemSelected(item:MenuItem) :Boolean{
        return when(item.itemId){
            R.id.item_liga ->{
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<TeamFragment>(R.id.MainFragmentContainer)
                }
                true
            }
            R.id.item_mundial ->{
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<WorldFragment>(R.id.MainFragmentContainer)
                }
                true
            }
            R.id.item_bdo ->{
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<OroFragment>(R.id.MainFragmentContainer)
                }
                true
            }
            R.id.item_aline ->{
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<MakeFragment>(R.id.MainFragmentContainer)
                }
                true
            }
            R.id.item_chat ->{
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<ChatFragment>(R.id.MainFragmentContainer)
                }
                true
            }
            else -> false
        }

    }

    fun showVideo(videoUrl: String) {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = MultimediaFragment.newInstance(videoUrl)
        transaction.add(android.R.id.content, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

     fun ToolBarName(name:String){
        binding.myToolbar.title = name
    }




}