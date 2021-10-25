package com.example.labor3quizandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ResultFragment : Fragment() {

    private lateinit var resultTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val rootView = inflater.inflate(R.layout.fragment_result, container, false)
        resultTextView = rootView.findViewById(R.id.resultTextView)

        val goodAnswers : Int? = arguments?.getInt(Constants.SCORE)
        val questions : Int? = arguments?.getInt(Constants.QUESTIONS)

        resultTextView.text = "$goodAnswers/$questions"
        return rootView
    }



}