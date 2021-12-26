package com.example.marketplaceproject.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.marketplaceproject.R
import com.example.marketplaceproject.activities.LoginActivity
import com.example.marketplaceproject.activities.MainActivity
import com.example.marketplaceproject.retrofit.accesLayers.ProductAccessLayer
import com.example.marketplaceproject.retrofit.accesLayers.UserAccessLayer
import com.example.marketplaceproject.utils.Constants
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.regex.Pattern
import kotlin.properties.Delegates

class AddProductFragment : Fragment() {

    private lateinit var titleTextInputLayout : TextInputLayout
    private lateinit var titleEditText : EditText

    private lateinit var ratingTextInputLayout : TextInputLayout
    private lateinit var ratingEditText : EditText

    private lateinit var amountTypeTextInputLayout : TextInputLayout
    private lateinit var amountTypeEditText : EditText

    private lateinit var priceTypeTextInputLayout : TextInputLayout
    private lateinit var priceTypeEditText : EditText

    private lateinit var pricePerUnitTextInputLayout : TextInputLayout
    private lateinit var pricePerUnitEditText : EditText
    private lateinit var arrow: ImageView

    private lateinit var shared: SharedPreferences

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switch : Switch
    private lateinit var addButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shared = requireActivity().getSharedPreferences(Constants.SHAREDPREF,Context.MODE_PRIVATE)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_product, container, false)

        titleTextInputLayout = rootView.findViewById(R.id.addTitleTextInputLayout)
        titleEditText = rootView.findViewById(R.id.addTitleEditText)

        ratingTextInputLayout = rootView.findViewById(R.id.addRatingTextInputLayout)
        ratingEditText = rootView.findViewById(R.id.addRatingEditText)

        amountTypeTextInputLayout = rootView.findViewById(R.id.addAmountTypeTextInputLayout)
        amountTypeEditText = rootView.findViewById(R.id.addAmountTypeEditText)

        priceTypeTextInputLayout = rootView.findViewById(R.id.addPriceTypeTextInputLayout)
        priceTypeEditText = rootView.findViewById(R.id.addPriceTypeEditText)

        pricePerUnitTextInputLayout = rootView.findViewById(R.id.addPricePerUnitTextInputLayout)
        pricePerUnitEditText = rootView.findViewById(R.id.addPricePerUnitEditText)

        switch = rootView.findViewById(R.id.switch1)

        arrow = rootView.findViewById(R.id.addProduct_arrow)

        addButton = rootView.findViewById(R.id.addProductButton)



        setOnClickListenerForUpdateButton()

        arrow.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction  = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.mainFragmentContainerView, TimeLineFragment())
            fragmentTransaction.commit()
        }

        return rootView
    }

    private fun setOnClickListenerForUpdateButton() {

        addButton.setOnClickListener {

            titleTextInputLayout.error = ""
            ratingTextInputLayout.error =""
            amountTypeTextInputLayout.error =""
            priceTypeTextInputLayout.error =""
            pricePerUnitTextInputLayout.error =""

            var areErrors = false

            if (titleEditText.text.isEmpty()) {
                titleTextInputLayout.error = "Please fill this field!"
                areErrors = true
            }

            if (ratingEditText.text.isEmpty()) {
                ratingTextInputLayout.error = "Please fill this field!"
                areErrors = true
            }

            else{
                if(ratingEditText.text.toString().toFloatOrNull() == null){
                    ratingTextInputLayout.error = "Add number!"
                    areErrors = true
                }
            }

            if (amountTypeEditText.text.isEmpty()) {
                amountTypeTextInputLayout.error = "Please fill this field!"
                areErrors = true
            }

            if (priceTypeEditText.text.isEmpty()) {
                priceTypeTextInputLayout.error = "Please fill this field!"
                areErrors = true
            }

            if (pricePerUnitEditText.text.isEmpty()) {
                pricePerUnitTextInputLayout.error = "Please fill this field!"
                areErrors = true
            }

            else{
                if(pricePerUnitEditText.text.toString().toIntOrNull() == null){
                    pricePerUnitTextInputLayout.error = "Add number!"
                    areErrors = true
                }
            }

        }

    }

    /*
    private fun createAddProductObserver(){
        disposable = ProductAccessLayer.AddProductsObservable(shared.getString(Constants.TOKEN,"")!!,titleEditText.text.toString(),"",pricePerUnitEditText.text.toString(),
        un)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    val edit = (activity as LoginActivity).shared.edit()
                    edit.putString(Constants.TOKEN, it.token)
                    edit.putString(Constants.USERNAME, it.userName)
                    edit.apply()
                    startActivity(intent)
                    requireActivity().finish()
                },
                {
                    val toast =
                        Toast.makeText(
                            context,
                            it.message.toString(),
                            Toast.LENGTH_LONG
                        )
                    toast.setGravity(Gravity.TOP, 0, 0)
                    toast.show()
                })
    }
    */


}