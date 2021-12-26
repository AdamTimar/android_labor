package com.example.marketplaceproject.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.marketplaceproject.models.Product
import com.example.marketplaceproject.models.UserDetails

class MainActivityViewModel : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    lateinit var userDetails : UserDetails
    lateinit var products : MutableList<Product>
    lateinit var myProducts : MutableList<Product>

}
