package com.example.labor5quiz

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
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class QuizStartFragment : Fragment() {

    private lateinit var yourNameEditText : EditText
    private lateinit var chooseContactButoon : Button
    private lateinit var getStartedButoon : Button
    private var userNamePhoneNumber: String? = null


    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_quizstart, container, false)

        yourNameEditText = rootView.findViewById<EditText>(R.id.yourNameTextInputField)
        chooseContactButoon = rootView.findViewById<Button>(R.id.chooseContactButton)
        getStartedButoon = rootView.findViewById<Button>(R.id.getStartedButton)
        getStartedButoon.isEnabled = false
        getStartedButoon.setBackgroundColor(Color.parseColor("#808080"))

        yourNameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                userNamePhoneNumber = yourNameEditText.text.toString()
                if(userNamePhoneNumber?.isEmpty()!! || userNamePhoneNumber == null) {
                    getStartedButoon.isEnabled = false
                    getStartedButoon.setBackgroundColor(Color.parseColor("#808080"))
                }
                else {
                    getStartedButoon.isEnabled = true
                    getStartedButoon.setBackgroundColor(Color.parseColor("#F57F24"))
                }
            }
        })

        chooseContactButoon.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = Phone.CONTENT_TYPE
            startActivityForResult(intent,111)
        }

        getStartedButoon.setOnClickListener {

            userNamePhoneNumber = null
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 111 && resultCode == Activity.RESULT_OK){
            var contactUri = data?.data ?: return
            var cols = arrayOf(Phone.DISPLAY_NAME,Phone.NUMBER)
            var rs = requireActivity().contentResolver.query(contactUri,cols,null,null,null)
            if(rs?.moveToFirst()!!){
                userNamePhoneNumber = rs.getString(0)+"\n"+rs.getString(1)
                yourNameEditText.setText(userNamePhoneNumber)
            }
        }

    }

}