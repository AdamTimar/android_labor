package com.example.marketplaceproject.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.marketplaceproject.R
import com.example.marketplaceproject.utils.Constants
import kotlin.properties.Delegates


class MyProductDetailsFragment : Fragment() {

    private lateinit var ownerNameTextView : TextView
    private lateinit var titleTextView : TextView
    private lateinit var priceTextView : TextView
    private lateinit var availableUnitsTextView : TextView
    private lateinit var descriptionTextView : TextView
    private lateinit var statusImageView : ImageView
    private lateinit var arrow : ImageView

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
    private lateinit var updateButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments

        rating = arguments?.getFloat(Constants.RATING)!!
        amountType = arguments?.getString(Constants.AMOUNTTYPE)!!
        priceType = arguments?.getString(Constants.PRICETYPE)!!
        productId = arguments?.getString(Constants.PRODUCTID)!!
        userName = arguments?.getString(Constants.USERNAME)!!
        isActive = arguments?.getBoolean(Constants.ISACTIVE)!!
        pricePerUnit = arguments?.getString(Constants.PRICEPERUNIT)!!.toIntOrNull()
        units = arguments?.getString(Constants.UNITS)!!.toIntOrNull()
        title = arguments?.getString(Constants.TITLE)!!
        description = arguments?.getString(Constants.DESCRIPTION)!!

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_my_product_details, container, false)

        ownerNameTextView = rootView.findViewById(R.id.productDetailOwnerName2)
        titleTextView = rootView.findViewById(R.id.productDetailProductName2)
        priceTextView = rootView.findViewById(R.id.productDetailProductPrice2)
        availableUnitsTextView = rootView.findViewById(R.id.availableAmountResult2)
        descriptionTextView = rootView.findViewById(R.id.productDescription2)
        statusImageView = rootView.findViewById(R.id.productStatus2)
        arrow = rootView.findViewById(R.id.product_detail_arrow2)
        updateButton = rootView.findViewById(R.id.updateProductButton)

        ownerNameTextView.text = userName
        titleTextView.text = title
        priceTextView.text = pricePerUnit.toString()+ " " + priceType + "/" + amountType
        availableUnitsTextView.text = units.toString() + " " + amountType
        descriptionTextView.text = description

        if(!isActive){
            statusImageView.setImageResource(R.drawable.inactive)
        }

        arrow.setOnClickListener {
            val manager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.replace(R.id.mainFragmentContainerView, MyMarketFragment())
            transaction.commit()
        }

        updateButton.setOnClickListener {

            val bundle = Bundle()

            bundle.putFloat(Constants.RATING, rating)
            bundle.putString(Constants.AMOUNTTYPE, amountType)
            bundle.putString(Constants.PRICETYPE, priceType)
            bundle.putString(Constants.PRODUCTID, productId)
            bundle.putString(Constants.USERNAME, userName)
            bundle.putBoolean(Constants.ISACTIVE, isActive)
            bundle.putInt(Constants.PRICEPERUNIT, pricePerUnit!!)
            bundle.putInt(Constants.UNITS, units!!)
            bundle.putString(Constants.DESCRIPTION, description)
            bundle.putString(Constants.TITLE, title)

            val manager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            val fragment = UpdateProductFragment()
            fragment.arguments = bundle
            transaction.replace(R.id.mainFragmentContainerView,fragment)
            transaction.commit()

        }

        return rootView
    }

}