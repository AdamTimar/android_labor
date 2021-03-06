package com.example.labor4quiz

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


class LoginFragment : Fragment() {

    private lateinit var yourNameEditText : EditText
    private lateinit var chooseContactButoon : Button
    private lateinit var getStartedButoon : Button
    private var userNamePhoneNumber: String? = null
    private var contactsPressed = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_login, container, false)

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
            contactsPressed = true
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = Phone.CONTENT_TYPE
            startActivityForResult(intent,111)
        }

        getStartedButoon.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra(Constants.NAMEPHONE,userNamePhoneNumber)
            startActivity(intent)
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