package com.example.labor5quiz.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.labor5quiz.R
import com.example.labor5quiz.retrofit.models.Quiz
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    var userName: String = "N/A"
    var phoneNumber: String? = null
    val highScores = mutableMapOf<Pair<String, String>, Int>()
    lateinit var questions: List<Quiz>

}
