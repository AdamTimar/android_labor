package com.example.labor5quiz.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.content.Intent
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.app.Activity
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.labor5quiz.MainActivity
import com.example.labor5quiz.R

class QuizStartFragment : Fragment() {

    private lateinit var yourNameEditText : EditText
    private lateinit var chooseContactButton : Button
    private lateinit var getStartedButton : Button
    private var userName : String? = null
    private var userPhoneNumber : String? = null
    private var contactWasSelected = false


    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_quizstart, container, false)

        yourNameEditText = rootView.findViewById<EditText>(R.id.yourNameTextInputField)
        chooseContactButton = rootView.findViewById<Button>(R.id.chooseContactButton)
        getStartedButton = rootView.findViewById<Button>(R.id.getStartedButton)
        getStartedButton.isEnabled = false
        getStartedButton.setBackgroundColor(Color.parseColor("#808080"))

        yourNameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (contactWasSelected){
                    userName = yourNameEditText.text.toString()
                    contactWasSelected = false
                    userPhoneNumber = null
                    val toast = Toast.makeText(context, "After modifying name you won't have phone number!", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.TOP, 0, 0)
                    toast.show()
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                userName = yourNameEditText.text.toString()
                if(userName == null || userName!!.isEmpty()) {
                    getStartedButton.isEnabled = false
                    getStartedButton.setBackgroundColor(Color.parseColor("#808080"))
                }
                else {
                    getStartedButton.isEnabled = true
                    getStartedButton.setBackgroundColor(Color.parseColor("#F57F24"))
                }
            }
        })

        chooseContactButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = Phone.CONTENT_TYPE
            startActivityForResult(intent,111)
        }

        getStartedButton.setOnClickListener {
            (activity as MainActivity).getMainActivityViewModel().userName = userName!!
            (activity as MainActivity).getMainActivityViewModel().phoneNumber = userPhoneNumber
            if((activity as MainActivity).getMainActivityViewModel().highScores.get(Pair(userName,userPhoneNumber)) == null) {
                (activity as MainActivity).getMainActivityViewModel().highScores.put((userName to userPhoneNumber) as Pair<String,String>,0)
            }
            contactWasSelected = false
            userName= null
            userPhoneNumber = null
            yourNameEditText.setText("")
            val manager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            val questionsFragment = QuestionFragment()
            transaction.replace(R.id.fragmentContainerView, questionsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return rootView
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 111 && resultCode == Activity.RESULT_OK){
            var contactUri = data?.data ?: return
            var cols = arrayOf(Phone.DISPLAY_NAME,Phone.NUMBER)
            var rs = requireActivity().contentResolver.query(contactUri,cols,null,null,null)
            if(rs?.moveToFirst()!!){
                userName = rs.getString(0)
                userPhoneNumber = rs.getString(1)
                yourNameEditText.setText(userName)
            }

            contactWasSelected = true
        }

    }

}