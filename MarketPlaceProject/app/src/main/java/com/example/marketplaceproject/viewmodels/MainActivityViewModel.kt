package com.example.marketplaceproject.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.marketplaceproject.retrofit.models.UserDetails

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    var userDetails : UserDetails? = null


}
