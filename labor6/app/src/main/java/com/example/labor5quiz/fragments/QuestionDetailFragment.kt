package com.example.labor5quiz.fragments

import android.R.attr
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.labor5quiz.R
import android.R.attr.fragment

import android.R.attr.key
import com.example.labor5quiz.Constants


class QuestionDetailFragment : Fragment(){

    private lateinit var questionTextView: TextView
    private lateinit var questionTypeTextView: TextView
    private lateinit var answer1TextView: TextView
    private lateinit var answer2TextView: TextView
    private lateinit var answer3TextView: TextView
    private lateinit var answer4TextView: TextView
    private var answersList : MutableList<String> = mutableListOf()
    private var correctAnswer = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_details,container,false)

        questionTextView = rootView.findViewById(R.id.detailsQuestion)
        questionTypeTextView = rootView.findViewById(R.id.detailsQuestionType)
        answer1TextView = rootView.findViewById(R.id.detailsAnswer1)
        answer2TextView = rootView.findViewById(R.id.detailsAnswer2)
        answer3TextView = rootView.findViewById(R.id.detailsAnswer3)
        answer4TextView = rootView.findViewById(R.id.detailsAnswer4)

        val bundle = arguments
        questionTextView.text = bundle?.getString(Constants.DETAILSQUESTION)
        questionTypeTextView.text = bundle?.getString(Constants.QUESTIONTYPE)
        val answer1 = bundle?.getString(Constants.ANSWER1)
        val answer2 = bundle?.getString(Constants.ANSWER2)
        val answer3 = bundle?.getString(Constants.ANSWER3)
        val answer4 = bundle?.getString(Constants.ANSWER4)
        correctAnswer = bundle?.getString(Constants.CORRECT_ANSWER)!!

        answersList.add(answer1!!)
        answersList.add(answer2!!)
        answersList.add(answer3!!)
        answersList.add(answer4!!)

        setCorrectAnswerFirst()

        return rootView
    }

    fun setCorrectAnswerFirst(){
        val list = answersList.filterNot { it == correctAnswer}
        answer1TextView.text = correctAnswer
        answer2TextView.text = list[0]
        answer3TextView.text = list[1]
        answer4TextView.text = list[2]
    }
}

