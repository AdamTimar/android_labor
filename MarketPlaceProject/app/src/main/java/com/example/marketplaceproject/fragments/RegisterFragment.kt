package com.example.marketplaceproject.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.example.marketplaceproject.retrofit.accesLayers.UserAccessLayer
import com.example.marketplaceproject.utils.Constants
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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
    private var registrationDisposable: Disposable? = null


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

    override fun onDestroy() {
        super.onDestroy()
        if(registrationDisposable!=null) {
            if (!registrationDisposable!!.isDisposed)
                registrationDisposable!!.dispose()
        }
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
            } else if (!Pattern.compile(Constants.EMAIL_REGEX_PATTERN).matcher(emailEditText.text)
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
                createRegistrationObserver()
            }

        }

    }

    private fun setOnClickListenerForLogInTextView() {
        logInTextView.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.loginFragmentContainerView, LoginFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    private fun createRegistrationObserver() {
        registrationDisposable = UserAccessLayer.getRegistrationObservable(
            emailEditText.text.toString(),
            usernameEditText.text.toString(),
            passwordEditText.text.toString()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {

                    val bundle = Bundle()
                    bundle.putString(Constants.EMAIL, emailEditText.text.toString())
                    bundle.putString(Constants.REGISTRATIONFEEDBACK, "passwordReset")

                    val feedBackFragment = FeedBackFragment()
                    feedBackFragment.arguments = bundle

                    Log.d("em", bundle.getString(Constants.EMAIL).toString())

                    val transaction = requireActivity().supportFragmentManager.beginTransaction()

                    transaction.replace(R.id.loginFragmentContainerView, feedBackFragment).addToBackStack(null).commit()
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
}

