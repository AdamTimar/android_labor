package com.example.marketplaceproject.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.marketplaceproject.R
import com.example.marketplaceproject.utils.Constants

class FeedBackFragment : Fragment() {

    private lateinit var emailSentTextView : TextView
    private lateinit var backToLoginButton : Button
    private lateinit var feedBackTypeTextView : TextView

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_feedback, container, false)

        emailSentTextView = rootView.findViewById(R.id.weWillSendAnEmailTextView)
        backToLoginButton = rootView.findViewById(R.id.backToLoginButton)
        feedBackTypeTextView = rootView.findViewById(R.id.feedBackTileTextView)

        Log.d("em2", arguments?.getString(Constants.EMAIL).toString())

        if(arguments?.getString(Constants.REGISTRATIONFEEDBACK) != null) {
            emailSentTextView.text = "We have sent a mail to ${arguments?.getString(Constants.EMAIL)}, check it then log in!"
            feedBackTypeTextView.text = "Activate your account"
        }

        if(arguments?.getString(Constants.PASSWORDCHANGEFEEDBACK) != null) {
            emailSentTextView.text = "We have sent a mail to ${arguments?.getString(Constants.EMAIL)}, which contains your new password, check it then log in!"
            feedBackTypeTextView.text = "Password changed"
        }
        setOnClickListenerForBackToLoginButton()
        return rootView
    }

    private fun setOnClickListenerForBackToLoginButton(){
        backToLoginButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.loginFragmentContainerView, LoginFragment())
                .commit()
        }
    }

}