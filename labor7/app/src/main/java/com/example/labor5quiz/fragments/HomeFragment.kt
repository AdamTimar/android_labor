package com.example.labor5quiz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.labor5quiz.R

class HomeFragment : Fragment() {

    private lateinit var testYourSkillsButton : Button
    private lateinit var readQuestionsButton : Button
    private lateinit var createQuestionButton : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_home,container,false)

        testYourSkillsButton = rootView.findViewById(R.id.testYourSkillsButton)
        readQuestionsButton = rootView.findViewById(R.id.readQuestionsButton)
        createQuestionButton = rootView.findViewById(R.id.createQuestionsButton)

        setOnclickListenersOnButtons()

        return rootView
    }

    private fun setOnclickListenersOnButtons(){
        testYourSkillsButton.setOnClickListener {

            val manager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            val questionsFragment = QuizStartFragment()
            transaction.replace(R.id.fragmentContainerView, questionsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        readQuestionsButton.setOnClickListener {

            val manager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            val questionsFragment = QuestionListFragment()
            transaction.replace(R.id.fragmentContainerView, questionsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        createQuestionButton.setOnClickListener {

            val manager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            val questionsFragment = AddQuestionFragment()
            transaction.replace(R.id.fragmentContainerView, questionsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}