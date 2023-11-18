package com.example.tutortime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class tutorHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tutor_home, container, false)
        val studentrequest: Button = view.findViewById(R.id.studentRequest)
        val messageStudent: Button = view.findViewById(R.id.messageStudents)
        val editProfile: Button = view.findViewById(R.id.editProfile)
        val setting: Button = view.findViewById(R.id.setting)

        studentrequest.setOnClickListener {
            findNavController().navigate(R.id.action_tutorHomeFragment_to_requestListFragment)
        }
        messageStudent.setOnClickListener {
            findNavController().navigate(R.id.action_tutorHomeFragment_to_tutorMessageFragment)
        }
        editProfile.setOnClickListener {
            findNavController().navigate(R.id.action_tutorHomeFragment_to_tutorProfilePrivateFragment)
        }
        setting.setOnClickListener {
            findNavController().navigate(R.id.action_tutorHomeFragment_to_tutorSettingsFragment)
        }

        return view
    }


}