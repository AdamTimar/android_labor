package com.example.marketplaceproject.fragments

import android.annotation.SuppressLint
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
import com.example.marketplaceproject.R
import com.example.marketplaceproject.utils.Constants
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class LoginFragment : Fragment() {

    private lateinit var emailEditText : EditText
    private lateinit var emailTextInputLayout: TextInputLayout
    private lateinit var passwordEditText : EditText
    private lateinit var passwordTextInputLayout : TextInputLayout
    private lateinit var loginButton : Button
    private lateinit var singUpButton : Button
    private lateinit var clickHereTextView : TextView

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)

        emailEditText = rootView.findViewById(R.id.emailEditText2)
        emailTextInputLayout = rootView.findViewById(R.id.emailTextInputLayout2)
        passwordEditText = rootView.findViewById(R.id.passwordEditText2)
        passwordTextInputLayout = rootView.findViewById(R.id.passwordTextInputLayout2)
        loginButton = rootView.findViewById(R.id.loginButton)
        singUpButton = rootView.findViewById(R.id.signUpButton)
        clickHereTextView = rootView.findViewById(R.id.clickHereTextView)

        setOnClickListenerForLoginButton()
        setOnClickListenerForSignUpButton()
        setOnClickListenerForClickMeTextView()

        return rootView
    }

    private fun setOnClickListenerForSignUpButton(){

        singUpButton.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction  = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.loginFragmentContainerView, RegisterFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    private fun setOnClickListenerForLoginButton(){

        loginButton.setOnClickListener{

            emailTextInputLayout.error = ""
            passwordTextInputLayout.error = ""

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

            if (passwordEditText.text.isEmpty()) {
                passwordTextInputLayout.error = "Please fill the password field!"
                areErrors = true
            }

            if (!areErrors) {
                val toast = Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP,0,0)
                toast.show()
            }

        }

    }

    private fun setOnClickListenerForClickMeTextView(){

        clickHereTextView.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction  = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.loginFragmentContainerView, ForgotPasswordFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

}