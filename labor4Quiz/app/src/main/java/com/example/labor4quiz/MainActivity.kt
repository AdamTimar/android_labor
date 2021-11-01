package com.example.labor4quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        val namePhone = intent.getStringExtra(Constants.NAMEPHONE)

        val bundle = Bundle()
        bundle.putString(Constants.NAMEPHONE,namePhone)

        val userDetailsFragment = UserDetailsFragment()
        userDetailsFragment.arguments = bundle
        transaction.replace(R.id.detailsFragmentContainerView, userDetailsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}