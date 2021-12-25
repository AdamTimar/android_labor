package com.example.marketplaceproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.marketplaceproject.R

class TimeLineFragment : Fragment() {

    private lateinit var avatar: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_time_line, container, false)

        avatar = rootView.findViewById(R.id.timeLineAvatarIcon)

        setOnclickListenerForAvatar()

        return rootView
    }

    private fun setOnclickListenerForAvatar(){

        avatar.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainerView, ProfileFragment())
                .commit()
        }
    }

}