package com.example.labor5quiz

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import com.example.labor5quiz.fragments.*
import com.example.labor5quiz.retrofit.accesLayers.QuestionAccessLayer
import com.example.labor5quiz.viewmodels.MainActivityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var drawer : DrawerLayout
    private lateinit var navigationView : NavigationView
    lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel = MainActivityViewModel(application)

        getQuestionsObserver(10, this)

        navigationView = findViewById(R.id.navigationView)
        drawer = findViewById(R.id.drawerLayout)

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END)
        drawer.openDrawer(GravityCompat.START)

    }

    fun getMainActivityViewModel(): MainActivityViewModel {
        return mainActivityViewModel
    }

    private fun getQuestionsObserver(amount: Int, context: Context) {
        disposable = QuestionAccessLayer.getQuestionsObservable(amount)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                        mainActivityViewModel.questions = it.results
                        mainActivityViewModel.questions.forEach{
                            (it.incorrectAnswers as MutableList<String>).add(it.correctAnswer)
                        }
                    setNavigationDrawerListener()
                    val homeFragment = HomeFragment()
                    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainerView, homeFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                },
                {
                    val toast =
                        Toast.makeText(context, "Unable to get questions from server", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.TOP, 0, 0)
                    toast.show()
                })
        }

    fun setNavigationDrawerListener(){
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
}


