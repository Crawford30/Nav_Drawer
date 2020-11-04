package com.example.navdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    lateinit var homeFragment: HomeFragment
    lateinit var workFragment: WorkFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lateinit var drawerLayout: DrawerLayout
        lateinit var navigationView: NavigationView
        lateinit var toolBar: Toolbar

        //=====Define the hooks====
         drawerLayout = findViewById(R.id.drawerLayout)
      navigationView = findViewById(R.id.nav_view)
        toolBar = findViewById(R.id.tooBar)


//========https://youtu.be/m1RV0HPuBWo?list=PL5jb9EteFAOD8dlG1Il3fCiaVNPD_P7gh&t=239===============
        //======Tool bar=======
        //tell the system that we are going to use this toolbar as our action bar
        //setSupportActionBar(toolBar)

        //action bar

        setSupportActionBar(toolBar)
        val actionBar = supportActionBar
        actionBar?.title = "Nav Drawer"

        val drawerToggle:ActionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawerLayout,toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        //implement nav item

        //defual fragment
        homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
       //create our fragments

        when (menuItem.itemId){
            R.id.home -> {

                homeFragment = HomeFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }


            R.id.work -> {

                workFragment = WorkFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, workFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {

            super.onBackPressed()

        }

    }
}