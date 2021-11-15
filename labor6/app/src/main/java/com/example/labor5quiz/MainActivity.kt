package com.example.labor5quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.example.labor5quiz.fragments.*
import com.example.labor5quiz.viewmodels.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var drawer : DrawerLayout
    private lateinit var navigationView : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = MainActivityViewModel(application)
        mainActivityViewModel.readQuestionsFromFile()

        navigationView = findViewById(R.id.navigationView)
        drawer = findViewById(R.id.drawerLayout)

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END)
        drawer.openDrawer(GravityCompat.START)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.isChecked) menuItem.isChecked = false else menuItem.isChecked = true

            drawer.closeDrawers()
            val newFragment: Fragment
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            when (menuItem.itemId) {
                R.id.nav_first_fragment -> {
                    newFragment = HomeFragment()
                    transaction.replace(R.id.fragmentContainerView, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }

                R.id.nav_second_fragment -> {
                    newFragment = QuizStartFragment()
                    transaction.replace(R.id.fragmentContainerView, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }

                R.id.nav_third_fragment -> {
                    newFragment = ProfileFragment()
                    transaction.replace(R.id.fragmentContainerView, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }

                R.id.nav_fourth_fragment -> {
                    newFragment = QuestionListFragment()
                    transaction.replace(R.id.fragmentContainerView, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }

                R.id.nav_fifth_fragment -> {
                    newFragment = AddQuestionFragment()
                    transaction.replace(R.id.fragmentContainerView, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }

                else -> {
                    Toast.makeText(applicationContext, "Somethings Wrong", Toast.LENGTH_SHORT)
                        .show()
                    true
                }
            }
        }

    }


    fun getMainActivityViewModel(): MainActivityViewModel {
        return mainActivityViewModel
    }




}

