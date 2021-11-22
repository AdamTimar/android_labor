package com.example.labor5quiz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.view.Gravity
import android.widget.Toast
import com.example.labor5quiz.MainActivity
import com.example.labor5quiz.R
import com.example.labor5quiz.retrofit.models.Quiz


class AddQuestionFragment: Fragment() {
    private lateinit var questionEditText: TextView
    private lateinit var answer1EditText: TextView
    private lateinit var answer2EditText: TextView
    private lateinit var answer3EditText: TextView
    private lateinit var answer4EditText: TextView
    private lateinit var addQuestionButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val rootView = inflater.inflate(R.layout.fragment_add_question, container, false)

        questionEditText = rootView.findViewById(R.id.questionText)
        answer1EditText = rootView.findViewById(R.id.answer1Text)
        answer2EditText = rootView.findViewById(R.id.answer2Text)
        answer3EditText = rootView.findViewById(R.id.answer3Text)
        answer4EditText = rootView.findViewById(R.id.answer4Text)
        addQuestionButton = rootView.findViewById(R.id.addQuestionButton);

        setListenersOnEditTexts()
        return rootView
    }

    fun setListenersOnEditTexts() {
        addQuestionButton.setOnClickListener {
            val question = questionEditText.text.toString()
            val answer1 = answer1EditText.text.toString()
            val answer2 = answer2EditText.text.toString()
            val answer3 = answer3EditText.text.toString()
            val answer4 = answer4EditText.text.toString()

            if (question.isNotEmpty() && answer1.isNotEmpty() && answer2.isNotEmpty() && answer3.isNotEmpty() && answer4.isNotEmpty()) {
                if (!textsAreDifferent(listOf(answer1, answer2, answer3, answer4))) {
                    val toast =
                        Toast.makeText(context, "Answer must be unique!", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.TOP, 0, 0)
                    toast.show()
                }
                else {
                    val quiz = Quiz("Any", "multiple", "medium", questionEditText.text.toString(), answer1, listOf(answer1,answer2,answer3,answer4))
                    ((activity as MainActivity).getMainActivityViewModel().questions as MutableList<Quiz>).add(
                        quiz
                    )
                    val toast = Toast.makeText(context, "Quiz added!", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.TOP, 0, 0)
                    toast.show()
                    questionEditText.text = ""
                    answer1EditText.text = ""
                    answer2EditText.text = ""
                    answer3EditText.text = ""
                    answer4EditText.text = ""
                }
            }
            else {
                val toast =
                    Toast.makeText(context, "Please fill all the fields!", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            }
        }
    }
}

fun textsAreDifferent(texts: List<String>): Boolean{
    texts.forEach{ t ->
        var current = t
        var counter = 0
        texts.forEach {
            if(it.compareTo(current,true) == 0){
                counter++
            }
        }
        if(counter > 1)
            return false
    }
    return true
}



