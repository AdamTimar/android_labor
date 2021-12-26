package com.example.marketplaceproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.marketplaceproject.R
import com.example.marketplaceproject.activities.MainActivity
import com.example.marketplaceproject.viewmodels.MainActivityViewModel

class ProfileFragment : Fragment() {

    private lateinit var emailTextView : TextView
    private lateinit var userNameTextView : TextView
    private lateinit var phoneNumberTextView : TextView
    private lateinit var phoneNumberHeaderTextView : TextView
    private lateinit var profileNameTextView : TextView
    private lateinit var arrowImageView : ImageView
    private lateinit var updateProfileButton: Button
    private lateinit var mainActivityViewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        emailTextView = rootView.findViewById(R.id.profileEmailTextView)
        userNameTextView = rootView.findViewById(R.id.profileUserNameTextView)
        phoneNumberTextView = rootView.findViewById(R.id.profilePhoneNumberTextView)
        phoneNumberHeaderTextView = rootView.findViewById(R.id.profilePhoneNumberHeaderTextView)
        profileNameTextView = rootView.findViewById(R.id.profileNameTextView)
        arrowImageView = rootView.findViewById(R.id.profile_arrow)
        updateProfileButton = rootView.findViewById(R.id.updateProfileButton)

        emailTextView.text = mainActivityViewModel.userDetails?.email
        userNameTextView.text = mainActivityViewModel.userDetails?.userName
        profileNameTextView.text = mainActivityViewModel.userDetails?.userName


        val phoneNumber = mainActivityViewModel.userDetails?.email
        if(phoneNumber == null){
            phoneNumberTextView.visibility = View.GONE
            phoneNumberHeaderTextView.visibility = View.GONE
        }

        else{
            phoneNumberTextView.text = mainActivityViewModel.userDetails?.phoneNumber.toString()
        }

        setOnClickListenerOnArrow()
        setOnClickListenerOnUpdateProfileButton()

        return rootView
    }

    private fun setOnClickListenerOnArrow(){

        arrowImageView.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainerView, TimeLineFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setOnClickListenerOnUpdateProfileButton(){

        updateProfileButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainerView, UpdateProfileFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}