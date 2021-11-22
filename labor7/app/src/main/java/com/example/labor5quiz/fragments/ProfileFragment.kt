package com.example.labor5quiz.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.labor5quiz.MainActivity
import com.example.labor5quiz.R

class ProfileFragment : Fragment() {

    private lateinit var playerNameTextView : TextView
    private lateinit var highScoreTextView : TextView
    private lateinit var myHighScore : TextView
    private lateinit var myName : TextView
    private lateinit var myPhoneNumber : TextView
    private lateinit var phoneNumberTextView : TextView


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        playerNameTextView = rootView.findViewById(R.id.playerNameTextView)

        highScoreTextView = rootView.findViewById(R.id.highScoreTextView)

        myPhoneNumber = rootView.findViewById(R.id.playersPhoneNumber)

        myHighScore = rootView.findViewById(R.id.highScore)

        myName = rootView.findViewById(R.id.playersName)

        phoneNumberTextView = rootView.findViewById(R.id.phoneNumberTextView)

        val viewModel = (activity as MainActivity).getMainActivityViewModel()

        myName.text = viewModel.userName

        if(viewModel.phoneNumber == null){
            phoneNumberTextView.visibility = View.GONE
            myPhoneNumber.visibility = View.GONE
        }

        else{
            myPhoneNumber.visibility = View.VISIBLE
            myPhoneNumber.text = viewModel.phoneNumber
        }

        val highScore = viewModel.highScores[Pair(viewModel.userName, viewModel.phoneNumber)]

        if(highScore == null){
            myHighScore.text = "0%"
        }
        else {
            myHighScore.text = (highScore.toFloat() / viewModel.questions.size * 100).toInt().toString() + "%"
        }
        return rootView
    }


}