package com.example.labor5quiz

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private lateinit var questionViewModel :  QuestionsViewModel
    lateinit var sharedPreferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences =  getSharedPreferences(Constants.MYSHAREDPREF,MODE_PRIVATE)
        questionViewModel = QuestionsViewModel(application)
        questionViewModel.readQuestionsFromFile()
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        val quizStartFragment = QuizStartFragment()
        transaction.replace(R.id.fragmentContainerView, quizStartFragment)
        transaction.commit()

    }

    fun getQuestionsViewModel() : QuestionsViewModel{
        return questionViewModel
    }

}