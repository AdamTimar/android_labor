package com.example.marketplaceproject.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.marketplaceproject.R
import com.example.marketplaceproject.utils.Constants
import kotlin.properties.Delegates


class ProductDetailsFragment : Fragment() {

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

        val rootView = inflater.inflate(R.layout.fragment_product_details, container, false)

        ownerNameTextView = rootView.findViewById(R.id.productDetailOwnerName)
        titleTextView = rootView.findViewById(R.id.productDetailProductName)
        priceTextView = rootView.findViewById(R.id.productDetailProductPrice)
        availableUnitsTextView = rootView.findViewById(R.id.availableAmountResult)
        descriptionTextView = rootView.findViewById(R.id.productDescription)
        statusImageView = rootView.findViewById(R.id.productStatus)
        arrow = rootView.findViewById(R.id.product_detail_arrow)

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
            transaction.replace(R.id.mainFragmentContainerView, TimeLineFragment())
            transaction.commit()
        }

        return rootView
    }

}