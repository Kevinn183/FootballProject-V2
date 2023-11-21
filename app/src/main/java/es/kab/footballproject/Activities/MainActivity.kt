package es.kab.footballproject.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationBarView
import es.kab.footballproject.Fragments.MakeFragment
import es.kab.footballproject.Fragments.WorldFragment
import es.kab.footballproject.Fragments.OroFragment
import es.kab.footballproject.Fragments.TeamFragment
import es.kab.footballproject.R
import es.kab.footballproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNav.setOnItemSelectedListener(this)
        setSupportActionBar(binding.myToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.actionLogOut ->{
                Toast.makeText(this,getString(R.string.exit),Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ControlActivity::class.java)
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
            else -> false
        }

    }

     fun ToolBarName(name:String){
        binding.myToolbar.title = name
    }




}