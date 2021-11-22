package com.example.labor5quiz.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.labor5quiz.Constants
import com.example.labor5quiz.MainActivity
import com.example.labor5quiz.R

class QuizEndFragment : Fragment() {

    private lateinit var resultTextView : TextView
    private lateinit var tryAgainButton : Button

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val rootView = inflater.inflate(R.layout.fragment_result, container, false)
        resultTextView = rootView.findViewById(R.id.resultTextView)

        tryAgainButton = rootView.findViewById(R.id.tryAgainButton)


        tryAgainButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }


        val goodAnswers : Int? = arguments?.getInt(Constants.SCORE)
        val questions : Int? = arguments?.getInt(Constants.QUESTIONS)

        resultTextView.text = "$goodAnswers/$questions"

        val viewModel = (activity as MainActivity).getMainActivityViewModel()

        val usersHighestScore = viewModel.highScores[Pair(viewModel.userName, viewModel.phoneNumber)]

        if(goodAnswers!! > usersHighestScore!!){
            viewModel.highScores[(viewModel.userName to viewModel.phoneNumber) as Pair<String, String>] = goodAnswers
        }

        return rootView
    }



}