package com.example.githubrepos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.githubrepos.ItemAction
import com.example.githubrepos.MainViewModel
import com.example.githubrepos.R
import com.example.githubrepos.adapter.RepositoriesAdapter
import com.example.githubrepos.databinding.ActivityMainBinding
import com.example.githubrepos.retrofit.ItemRepos

class MainActivity : AppCompatActivity(),ItemAction {
    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    private val repositoriesAdapter= RepositoriesAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search,menu)
        val myActionMenuItem = menu?.findItem(R.id.search)
        val searchView=myActionMenuItem?.actionView as SearchView
        //Реализация поиска
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query!="") {
                    model.getRepositoriesFromInternet(query)
                    //todo check for equality
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return item.onNavDestinationSelected(findNavController(R.id.my_nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    override fun saveItem(itemRepos: ItemRepos) {
        model.saveItem(itemRepos,this)
    }

    override fun openBrowser(itemRepos: ItemRepos) {
        model.openBrowserWithContext(itemRepos,this)
    }


}