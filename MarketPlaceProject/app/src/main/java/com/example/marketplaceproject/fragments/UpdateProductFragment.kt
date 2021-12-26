package com.example.marketplaceproject.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.marketplaceproject.R
import com.example.marketplaceproject.utils.Constants
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception
import java.util.regex.Pattern
import kotlin.properties.Delegates

class UpdateProductFragment : Fragment() {

    private var rating by Delegates.notNull<Float>()
    private lateinit var amountType: String
    private lateinit var priceType: String
    private lateinit var productId: String
    private lateinit var userName: String
    private var isActive by Delegates.notNull<Boolean>()
    private var pricePerUnit : Int? = null
    private var units : Int? = null
    private lateinit var title: String
    private lateinit var description: String

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

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switch : Switch
    private lateinit var updateButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rating = arguments?.getFloat(Constants.RATING)!!
        amountType = arguments?.getString(Constants.AMOUNTTYPE)!!
        priceType = arguments?.getString(Constants.PRICETYPE)!!
        productId = arguments?.getString(Constants.PRODUCTID)!!
        userName = arguments?.getString(Constants.USERNAME)!!
        isActive = arguments?.getBoolean(Constants.ISACTIVE)!!
        try {
            pricePerUnit = arguments?.getString(Constants.PRICEPERUNIT)!!.toIntOrNull()
        }
        catch(ex: Exception){
            pricePerUnit = 1
        }

        try {
            units = arguments?.getString(Constants.UNITS)!!.toIntOrNull()
        }
        catch(ex: Exception) {
            units = 1
        }
        title = arguments?.getString(Constants.TITLE)!!
        description = arguments?.getString(Constants.DESCRIPTION)!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_update_product, container, false)

        titleTextInputLayout = rootView.findViewById(R.id.updateTitleTextInputLayout)
        titleEditText = rootView.findViewById(R.id.updateTitleEditText)

        ratingTextInputLayout = rootView.findViewById(R.id.updateRatingTextInputLayout)
        ratingEditText = rootView.findViewById(R.id.updateRatingEditText)

        amountTypeTextInputLayout = rootView.findViewById(R.id.updateAmountTypeTextInputLayout)
        amountTypeEditText = rootView.findViewById(R.id.updateAmountTypeEditText)

        priceTypeTextInputLayout = rootView.findViewById(R.id.updatePriceTypeTextInputLayout)
        priceTypeEditText = rootView.findViewById(R.id.updatePriceTypeEditText)

        pricePerUnitTextInputLayout = rootView.findViewById(R.id.updatePricePerUnitTextInputLayout)
        pricePerUnitEditText = rootView.findViewById(R.id.updatePricePerUnitEditText)

        switch = rootView.findViewById(R.id.switch1)

        arrow = rootView.findViewById(R.id.updateProduct_arrow)

        updateButton = rootView.findViewById(R.id.updateProductButton)

        titleEditText.setText(title)
        ratingEditText.setText(rating.toString())
        amountTypeEditText.setText(amountType)
        pricePerUnitEditText.setText(pricePerUnit.toString())
        priceTypeEditText.setText(priceType)
        pricePerUnitEditText.setText(pricePerUnit.toString())

        switch.isChecked = isActive

        setOnClickListenerForUpdateButton()

        arrow.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction  = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.mainFragmentContainerView, MyMarketFragment())
            fragmentTransaction.commit()
        }

        return rootView
    }

    private fun setOnClickListenerForUpdateButton() {

        updateButton.setOnClickListener {

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

}