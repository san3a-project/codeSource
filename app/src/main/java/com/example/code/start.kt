package com.example.code

import kotlinx.android.synthetic.main.activity_start.*


import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class start : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,  HorizontalListViewFragment.OnListFragmentInteractionListener,     homeFragment.OnHomeFragmentInteractionListener {
    // TODO: Update argument type and name  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        fab.isVisible = false

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        /*   val f = ProfileDetailFragment.newInstance("hi","Hi") as Fragment
           supportFragmentManager
               .beginTransaction()
               .add(R.id.frame_container,f)
               .commit()*/
        val f = homeFragment.newInstance("hi","Hi") as Fragment
        supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_container,f)
                .commit()



    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                val f = ProfileDetailFragment.newInstance("hi","Hi") as Fragment
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container,f)
                        .commit()            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {
                val f = homeFragment.newInstance("hi","Hi") as Fragment
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container,f)
                        .commit()
            }
            R.id.nav_newSen3a -> {
                val f = FormulaireListing.newInstance("hi","Hi") as Fragment
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container,f)
                        .commit()
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment).addToBackStack("tag").commit();


    }


    override fun onListFragmentInteraction(item:Fruit){
        Log.e("erreur frag ", "enter")
        // 2
        //  Toast.makeText(this.applicationContext, "helloo"+item?.id, Toast.LENGTH_LONG).show()
        //  drawer_layout.removeDrawerListener()
        Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show()
        val f = ImageHorizontalListFragment.newInstance("hi","Hi") as Fragment
        replaceFragment(f)
        //    enableViews(true, "detail")


    }


    override   fun onHomeBackInteraction(uri: Uri){
        val f = homeFragment.newInstance("hi","Hi") as Fragment
        Log.e("back","baack")
        replaceFragment(f)

    }


}

