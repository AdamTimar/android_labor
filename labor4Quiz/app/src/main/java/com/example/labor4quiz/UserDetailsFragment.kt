package com.example.labor4quiz
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class UserDetailsFragment : Fragment() {

    private var userNamePhoneNumber: String? = null
    private lateinit var yourNameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userNamePhoneNumber = arguments?.getString(Constants.NAMEPHONE).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_userdetails, container, false)

        rootView.findViewById<TextView>(R.id.yourNameTextView).text = userNamePhoneNumber
        return rootView

    }





}