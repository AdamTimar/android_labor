package com.example.marketplaceproject.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marketplaceproject.R
import com.example.marketplaceproject.fragments.LoginFragment
import com.example.marketplaceproject.fragments.TimeLineFragment
import com.example.marketplaceproject.retrofit.accesLayers.UserAccessLayer
import com.example.marketplaceproject.retrofit.models.UserDetails
import com.example.marketplaceproject.utils.Constants
import com.example.marketplaceproject.viewmodels.MainActivityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel : MainActivityViewModel
    private lateinit var userDetailsDisposable: Disposable

    lateinit var shared : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shared = getSharedPreferences(Constants.SHAREDPREF , Context.MODE_PRIVATE)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel = MainActivityViewModel(application)

        val userName = shared.getString(Constants.USERNAME, "")
        createUserDetailsObserver(userName!!)

    }

    override fun onDestroy() {
        super.onDestroy()
        if(userDetailsDisposable != null){
            if(!userDetailsDisposable.isDisposed)
                userDetailsDisposable.dispose()
        }

    }

    override fun onBackPressed() {
        return
    }

    private fun createUserDetailsObserver(userName: String){

        userDetailsDisposable = UserAccessLayer.getUserDetailsObservable(
            userName
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Log.d("datas","success")
                    mainActivityViewModel.userDetails = UserDetails(it.userName, it.email, it.phoneNumber)
                    Log.d("datas", mainActivityViewModel.userDetails.toString())

                    this.supportFragmentManager.beginTransaction()
                        .add(R.id.mainFragmentContainerView, TimeLineFragment())
                        .commit()
                },
                {

                    Log.d("datas","error")
                    val toast =
                        Toast.makeText(
                            this,
                            it.message.toString(),
                            Toast.LENGTH_LONG
                        )
                    toast.setGravity(Gravity.TOP, 0, 0)
                    toast.show()
                })
    }
}