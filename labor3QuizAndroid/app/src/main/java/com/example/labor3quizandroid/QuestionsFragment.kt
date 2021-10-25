package com.example.labor3quizandroid

import Quiz
import QuizController
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class QuestionsFragment : Fragment() {

    private lateinit var quizController: QuizController
    private lateinit var button1 : Button
    private lateinit var button2 : Button
    private lateinit var button3 : Button
    private lateinit var button4 : Button
    private lateinit var questionTextView : TextView
    private var questionCounter : Int = 0
    private var goodAnswers = 0
    private var goodAnswerPosition = 0



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        var quizes = readQuestionsFromFile()
        quizController = QuizController(quizes)
        quizController.randomizeQuestions()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_questions, container, false)
        button1 = rootView.findViewById<Button>(R.id.button)
        button2 = rootView.findViewById<Button>(R.id.button2)
        button3 = rootView.findViewById<Button>(R.id.button3)
        button4 = rootView.findViewById<Button>(R.id.button4)
        questionTextView = rootView.findViewById<TextView>(R.id.questionTextView)


        setTextForButtonsAndTextView()

        setListenersOnButtons()

        return rootView
    }

    private fun readQuestionsFromFile() : List<Quiz>{

        val inputStream: InputStream = resources.openRawResource(R.raw.questions)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        var question: String?
        var answ1: String
        var answ2: String
        var answ3: String
        var answ4: String
        var goodAnsw: String
        var quiz : Quiz
        var quizes = mutableListOf<Quiz>()
        while ((bufferedReader.readLine().also { question = it })!= null) {
            println(question)
            answ1 = bufferedReader.readLine()
            answ2 = bufferedReader.readLine()
            answ3 = bufferedReader.readLine()
            answ4 = bufferedReader.readLine()
            goodAnsw = bufferedReader.readLine()

            quiz = Quiz(question!!, listOf(answ1,answ2,answ3,answ4),goodAnsw)
            quizes.add(quiz)
        }

        return quizes

    }

    private fun setTextForButtonsAndTextView() {
        if (questionCounter== quizController.quizList.size) {

            val bundle = Bundle()
            bundle.putInt(Constants.SCORE,goodAnswers)
            bundle.putInt(Constants.QUESTIONS,quizController.quizList.size)

            val manager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            val resultFragment = ResultFragment()
            resultFragment.arguments = bundle
            transaction.replace(R.id.fragmentContainerView, resultFragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }
        else {
            goodAnswerPosition = findGoodAnswerPosition()
            questionTextView.text = quizController.quizList[questionCounter].text
            button1.text = quizController.quizList[questionCounter].answers[0]
            button2.text = quizController.quizList[questionCounter].answers[1]
            button3.text = quizController.quizList[questionCounter].answers[2]
            button4.text = quizController.quizList[questionCounter].answers[3]
        }
    }

    private fun setListenersOnButtons(){
        button1.setOnClickListener {
            if(goodAnswerPosition == 0) {
                goodAnswers++
            }
            questionCounter++
            setTextForButtonsAndTextView()
        }

        button2.setOnClickListener {
            if(goodAnswerPosition == 1)
                goodAnswers++
            questionCounter++
            setTextForButtonsAndTextView()
        }

        button3.setOnClickListener {
            if(goodAnswerPosition == 2)
                goodAnswers++
            questionCounter++
            setTextForButtonsAndTextView()
        }

        button4.setOnClickListener {
            if(goodAnswerPosition == 3)
                goodAnswers++
            questionCounter++
            setTextForButtonsAndTextView()
        }

    }

    private fun findGoodAnswerPosition() : Int{
        for (i in 0..quizController.quizList[questionCounter].answers.size-1){
            if(quizController.quizList[questionCounter].answers[i].compareTo(quizController.quizList[questionCounter].goodAnswer)==0){
                return i
            }
        }
        return -1
    }
}