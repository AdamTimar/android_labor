package com.example.marketplaceproject.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.marketplaceproject.R
import com.example.marketplaceproject.activities.MainActivity
import com.example.marketplaceproject.retrofit.accesLayers.UserAccessLayer
import com.example.marketplaceproject.retrofit.models.UserDetails
import com.example.marketplaceproject.utils.Constants
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Integer.parseInt
import java.util.regex.Pattern


class UpdateProfileFragment : Fragment() {

    private lateinit var arrowBack : ImageView
    private lateinit var userNameTextInputLayout: TextInputLayout
    private lateinit var userNameEditText: EditText
    private lateinit var phoneNumberTextInputLayout: TextInputLayout
    private lateinit var phoneNumberEditText: EditText
    private lateinit var updateProfileButton: Button
    private lateinit var updatePasswordEditText: EditText
    private var updateProfileDisposable: Disposable? = null
    private var changePasswordDisposable: Disposable? = null

    lateinit var shared : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shared = requireActivity().getSharedPreferences(Constants.SHAREDPREF , Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_update_profile, container, false)

        arrowBack = rootView.findViewById(R.id.updateProfile_arrow)
        userNameTextInputLayout = rootView.findViewById(R.id.updateUserNameTextInputLayout)
        userNameEditText = rootView.findViewById(R.id.updateUserNameEditText)
        phoneNumberTextInputLayout = rootView.findViewById(R.id.updatePhoneNumberTextInputLayout)
        phoneNumberEditText = rootView.findViewById(R.id.updatePhoneNumberEditText)
        updateProfileButton = rootView.findViewById(R.id.updateProfileButton2)
        updatePasswordEditText = rootView.findViewById(R.id.updatePasswordEditText)

        userNameEditText.setText((activity as MainActivity).mainActivityViewModel.userDetails?.userName)

        val phoneNumber = (activity as MainActivity).mainActivityViewModel.userDetails?.phoneNumber

        if(phoneNumber != null)
            phoneNumberEditText.setText(phoneNumber.toString())

        setOnClickListenerForUpdateButton()

        arrowBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainerView, ProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        if(updateProfileDisposable != null) {
            if (!updateProfileDisposable!!.isDisposed)
                updateProfileDisposable!!.dispose()
        }

        if(changePasswordDisposable != null) {
            if (!changePasswordDisposable!!.isDisposed)
                changePasswordDisposable!!.dispose()
        }
    }

    private fun setOnClickListenerForUpdateButton() {

        updateProfileButton.setOnClickListener {

            userNameTextInputLayout.error = ""
            phoneNumberTextInputLayout.error = ""

            var areErrors = false

            if (userNameEditText.text.isEmpty()) {
                userNameTextInputLayout.error = "Please fill the username field!"
                areErrors = true
            }

            if (phoneNumberEditText.text.isEmpty()) {
                phoneNumberTextInputLayout.error = "Please fill the email field!"
                areErrors = true
            } else
             {
                 var numeric = true

                 try {
                     val num = parseInt(phoneNumberEditText.text.toString())
                 } catch (e: NumberFormatException) {
                     numeric = false
                 }

                if(!numeric) {
                    phoneNumberTextInputLayout.error = "Phone number must contain only numbers!"
                    areErrors = true
                }
            }

            if (!areErrors) {
                createUpdateUserObserver()
            }

        }

    }

    @SuppressLint("CommitPrefEdits")
    private fun createUpdateUserObserver() {
        updateProfileDisposable = UserAccessLayer.getUpdateUserDetailsObservable(
            userNameEditText.text.toString(),
            phoneNumberEditText.text.toString(),
            shared.getString(Constants.TOKEN, "")!!
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    (activity as MainActivity).mainActivityViewModel.userDetails =
                        UserDetails(it.userName, it.email, it.phoneNumber)
                    Log.d("oldToken", shared.getString(Constants.TOKEN,"")!!)
                    shared.edit().remove(Constants.TOKEN)
                    shared.edit().putString(Constants.TOKEN, it.token).apply()
                    Log.d("newToken", shared.getString(Constants.TOKEN,"")!!)
                    if(updatePasswordEditText.text.isEmpty()) {
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.mainFragmentContainerView, ProfileFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                    else{
                        createChangePasswordWithTokenUserObserver()
                    }
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


    private fun createChangePasswordWithTokenUserObserver() {
        changePasswordDisposable = UserAccessLayer.getResetPasswordWithTokenObservable(
            shared.getString(Constants.TOKEN, "")!!,
            updatePasswordEditText.text.toString()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragmentContainerView, ProfileFragment())
                        .addToBackStack(null)
                        .commit()
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