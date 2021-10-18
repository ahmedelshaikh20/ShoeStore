package com.udacity.shoestore

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
  private lateinit var drawerLayout: DrawerLayout
  lateinit var model: ShoeViewModel
  lateinit var sharedPreferences:SharedPreferences
  lateinit var Editor: SharedPreferences.Editor
  lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
      supportActionBar?.setTitle("ShoesApp")

      model = ViewModelProvider(this).get(ShoeViewModel::class.java)
      setUpSharedPreference()
       navController = this.findNavController(R.id.myNavHostFragment)
//      if (sharedPreferences.getBoolean("IsBoardedSuccessfully" , false)==false){
//        navController.navigate(R.id.action_loginFragment_to_welcomeFragment)
//
//      }
//      if(sharedPreferences.getBoolean("IsLogged" , false) == false){
//          Log.i("MainActivity" , "No u arenot logged")
//      }else {
//        navController.navigate(R.id.action_loginFragment_to_shoeListFragment)
//      }
      val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
      NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

      drawerLayout = binding.drawerLayout
     // NavigationUI.setupActionBarWithNavController(this, navController , drawerLayout)
      NavigationUI.setupWithNavController(binding.navView , navController)
    }

  private fun setUpSharedPreference() {
     sharedPreferences = this?.getPreferences(Context.MODE_PRIVATE) ?: return
    Editor = sharedPreferences.edit()

  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu ,menu)

    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.itemId
    if(id == R.id.LogOut){

     navController.navigate(R.id.action_shoeListFragment_to_loginFragment)
      Editor.putBoolean("IsLogged" , false)
      Editor.apply()


    }
    return super.onOptionsItemSelected(item)
  }

  override fun onNavigateUp(): Boolean {

    return true
  }



//  override fun onSupportNavigateUp(): Boolean {
//    val navController = this.findNavController(R.id.myNavHostFragment)
//    return NavigationUI.navigateUp(navController, drawerLayout)
//  }
}
