package com.example.labor3quizandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_login, container, false)

        rootView.findViewById<Button>(R.id.getStartedButton).setOnClickListener {
            val manager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            val questionsFragment = QuestionsFragment()
            transaction.replace(R.id.fragmentContainerView, questionsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        rootView.findViewById<Button>(R.id.chooseContactButton).setOnClickListener {
            val manager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            val contactsFragment = ContactsFragment()
            transaction.replace(R.id.fragmentContainerView, contactsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return rootView

    }

}