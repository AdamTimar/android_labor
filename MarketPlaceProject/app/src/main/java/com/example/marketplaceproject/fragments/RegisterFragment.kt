package com.example.marketplaceproject.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.DrawableCompat
import com.example.marketplaceproject.R
import com.example.marketplaceproject.utils.Constants
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern




class RegisterFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var logInTextView: TextView
    private lateinit var registerButton: Button
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var usernameInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_register, container, false)
        emailEditText = rootView.findViewById(R.id.emailEditText)
        usernameEditText = rootView.findViewById(R.id.usernameEditText)
        passwordEditText = rootView.findViewById(R.id.passwordEditText)
        logInTextView = rootView.findViewById(R.id.loginTextView)
        registerButton = rootView.findViewById(R.id.registerButton)
        emailInputLayout = rootView.findViewById(R.id.emailTextInputLayout)
        usernameInputLayout = rootView.findViewById(R.id.usernameTextInputLayout)
        passwordInputLayout = rootView.findViewById(R.id.passwordTextInputLayout)

        setOnClickListenerForRegisterButton()
        setOnClickListenerForLogInTextView()

        return rootView
    }

    private fun setOnClickListenerForRegisterButton() {

        registerButton.setOnClickListener {

            emailInputLayout.error = ""
            usernameInputLayout.error = ""
            passwordInputLayout.error = ""

            var areErrors = false

            if (usernameEditText.text.isEmpty()) {
                usernameInputLayout.error = "Please fill the username field!"
                areErrors = true
            }

            if (emailEditText.text.isEmpty()) {
                emailInputLayout.error = "Please fill the email field!"
                areErrors = true
            }

            else if (!Pattern.compile(Constants.EMAIL_REGEX_PATTERN).matcher(emailEditText.text)
                    .matches()
            ) {
                emailInputLayout.error = "Not an email!"
                areErrors = true
            }

            if (passwordEditText.text.isEmpty()) {
                passwordInputLayout.error = "Please fill the password field!"
                areErrors = true
            }

            if (!areErrors) {
                val toast = Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP,0,0)
                toast.show()
            }

        }

    }

    private fun setOnClickListenerForLogInTextView(){
        logInTextView.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction  = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.loginFragmentContainerView, LoginFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

}