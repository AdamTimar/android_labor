package com.example.labor5quiz.retrofit.models

import com.google.gson.annotations.SerializedName

class Quiz(
    @SerializedName("catgory")
    val category: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("difficulty")
    val difficulty: String,
    @SerializedName("question")
    val question: String,
    @SerializedName("correct_answer")
    val correctAnswer: String,
    @SerializedName("incorrect_answers")
    val incorrectAnswers: List<String>)
{}