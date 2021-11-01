package com.example.labor5quiz

import Quiz
import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class QuestionsViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    lateinit var questions : List<Quiz>

    fun readQuestionsFromFile() {

        val inputStream: InputStream = context.resources.openRawResource(R.raw.questions)
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

        questions = quizes
    }
}







