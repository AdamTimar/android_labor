package com.example.marketplaceproject.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.marketplaceproject.R
import com.example.marketplaceproject.fragments.MyMarketFragment
import com.example.marketplaceproject.fragments.ProfileFragment
import com.example.marketplaceproject.fragments.TimeLineFragment
import com.example.marketplaceproject.models.Product
import com.example.marketplaceproject.retrofit.accesLayers.UserAccessLayer
import com.example.marketplaceproject.models.UserDetails
import com.example.marketplaceproject.retrofit.accesLayers.ProductAccessLayer
import com.example.marketplaceproject.utils.Constants
import com.example.marketplaceproject.viewmodels.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel : MainActivityViewModel

    private lateinit var userDetailsDisposable: Disposable
    private lateinit var navigationView : BottomNavigationView

    lateinit var shared : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shared = getSharedPreferences(Constants.SHAREDPREF , Context.MODE_PRIVATE)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        navigationView = findViewById(R.id.bottom_navigation_view)

        setOnClickListenerForNavigationItems()

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

                    createProductsObserver(shared.getString(Constants.TOKEN,"")!!)

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

    private fun createProductsObserver(token: String){

        userDetailsDisposable = ProductAccessLayer.getProductsObservable(token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Log.d("datas","success")
                    mainActivityViewModel.products = it.filter{it.ownerName != shared.getString(Constants.USERNAME,"")} as MutableList<Product>
                    mainActivityViewModel.myProducts = it.filterNot{it.ownerName != shared.getString(Constants.USERNAME,"")} as MutableList<Product>
                    Log.d("productss", mainActivityViewModel.products[0].toString())

                    this.supportFragmentManager.beginTransaction()
                        .add(R.id.mainFragmentContainerView, TimeLineFragment())
                        .commit()
                },
                {

                    Log.d("products","error")
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

    private fun setOnClickListenerForNavigationItems() {

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
            val newFragment: Fragment
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.timelineItem -> {
                    newFragment = TimeLineFragment()
                    transaction.replace(R.id.mainFragmentContainerView, newFragment)
                    transaction.commit()
                    true
                }

                R.id.myMarketItem -> {
                    newFragment = MyMarketFragment()
                    transaction.replace(R.id.mainFragmentContainerView, newFragment)
                    transaction.commit()
                    true
                }

            }
            false
        }
            navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}