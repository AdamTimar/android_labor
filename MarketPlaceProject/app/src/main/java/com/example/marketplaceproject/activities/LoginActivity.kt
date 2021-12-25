package com.example.marketplaceproject.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketplaceproject.R
import com.example.marketplaceproject.utils.Constants

class LoginActivity : AppCompatActivity() {

    lateinit var shared : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        shared = getSharedPreferences(Constants.SHAREDPREF , Context.MODE_PRIVATE)

        val edit = shared.edit()
        edit.apply()
    }
}