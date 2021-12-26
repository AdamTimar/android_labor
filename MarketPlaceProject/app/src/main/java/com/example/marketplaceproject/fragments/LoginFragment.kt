package com.example.marketplaceproject.fragments

import android.annotation.SuppressLint
import android.content.Intent
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
import com.example.marketplaceproject.activities.LoginActivity
import com.example.marketplaceproject.activities.MainActivity
import com.example.marketplaceproject.retrofit.accesLayers.UserAccessLayer
import com.example.marketplaceproject.utils.Constants
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.regex.Pattern

class LoginFragment : Fragment() {

    private lateinit var userNameEditText : EditText
    private lateinit var userNameTextInputLayout: TextInputLayout
    private lateinit var passwordEditText : EditText
    private lateinit var passwordTextInputLayout : TextInputLayout
    private lateinit var loginButton : Button
    private lateinit var singUpButton : Button
    private lateinit var clickHereTextView : TextView
    private var loginDisposable : Disposable? = null

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)

        userNameEditText = rootView.findViewById(R.id.userNameEditText2)
        userNameTextInputLayout = rootView.findViewById(R.id.userNameTextInputLayout2)
        passwordEditText = rootView.findViewById(R.id.passwordEditText2)
        passwordTextInputLayout = rootView.findViewById(R.id.passwordTextInputLayout2)
        loginButton = rootView.findViewById(R.id.loginButton)
        singUpButton = rootView.findViewById(R.id.signUpButton)
        clickHereTextView = rootView.findViewById(R.id.clickHereTextView)

        setOnClickListenerForLoginButton()
        //goo()
        setOnClickListenerForSignUpButton()
        setOnClickListenerForClickMeTextView()

        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        if(loginDisposable!=null) {
            if (!loginDisposable!!.isDisposed)
                loginDisposable!!.dispose()
        }
    }

    private fun setOnClickListenerForSignUpButton(){

        singUpButton.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction  = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.loginFragmentContainerView, RegisterFragment())
            fragmentTransaction.commit()
        }
    }

    private fun setOnClickListenerForLoginButton(){

        loginButton.setOnClickListener{

            userNameTextInputLayout.error = ""
            passwordTextInputLayout.error = ""

            var areErrors = false

            if (userNameEditText.text.isEmpty()) {
                userNameTextInputLayout.error = "Please fill the email field!"
                areErrors = true
            }


            if (passwordEditText.text.isEmpty()) {
                passwordTextInputLayout.error = "Please fill the password field!"
                areErrors = true
            }

            if (!areErrors) {
                createLoginObserver()
            }

        }

    }

    private fun goo(){
        loginButton.setOnClickListener{

            userNameEditText.setText("AdamTimar")
            passwordEditText.setText("12345678")

            createLoginObserver()

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

    private fun createLoginObserver(){
        loginDisposable = UserAccessLayer.getLoginObservable(
            userNameEditText.text.toString(),
            passwordEditText.text.toString(),
        )
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

}