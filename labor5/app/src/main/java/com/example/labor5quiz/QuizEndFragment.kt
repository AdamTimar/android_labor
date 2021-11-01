package com.example.labor5quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class QuizEndFragment : Fragment() {

    private lateinit var resultTextView : TextView
    private lateinit var tryAgainButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        return rootView
    }



}