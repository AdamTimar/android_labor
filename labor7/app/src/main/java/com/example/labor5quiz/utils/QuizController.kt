package com.example.labor5quiz.utils

import com.example.labor5quiz.retrofit.models.Quiz

class QuizController(var quizList : List<Quiz>) {

    fun randomizeQuestions() {
        (quizList as MutableList<Quiz>).shuffle()
        for (i in (0..quizList.size - 1)) {
            (quizList[i].incorrectAnswers as MutableList<String>).shuffle()
        }
    }
}