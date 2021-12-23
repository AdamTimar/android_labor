package com.example.marketplaceproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.marketplaceproject.R
import com.example.marketplaceproject.utils.Constants
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern


class ForgotPasswordFragment : Fragment() {

    private lateinit var emailTextInputLayout : TextInputLayout
    private lateinit var emailEditText: EditText
    private lateinit var emailMeButton : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_forgot_password, container, false)

        emailTextInputLayout = rootView.findViewById(R.id.emailTextInputLayout3)
        emailEditText = rootView.findViewById(R.id.emailEditText3)
        emailMeButton = rootView.findViewById(R.id.emailMeButton)

        setOnClickListenerForEmailMeButton()

        return rootView
    }

    private fun setOnClickListenerForEmailMeButton(){

        emailMeButton.setOnClickListener {

            emailTextInputLayout.error = ""

            var areErrors = false

            if (emailEditText.text.isEmpty()) {
                emailTextInputLayout.error = "Please fill the email field!"
                areErrors = true
            }

            else if (!Pattern.compile(Constants.EMAIL_REGEX_PATTERN).matcher(emailEditText.text)
                    .matches()
            ) {
                emailTextInputLayout.error = "Not an email!"
                areErrors = true
            }
        }
    }

}