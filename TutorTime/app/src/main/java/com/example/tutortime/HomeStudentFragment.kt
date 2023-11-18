package com.example.tutortime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class HomeStudentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_student, container, false)
        val findTutorButton: Button = view.findViewById(R.id.findTutors)
        val messageTutorButton: Button = view.findViewById(R.id.messageTutors)
        val editButton: Button = view.findViewById(R.id.editProfile)
        val settingsButton: Button = view.findViewById(R.id.setting)
        // this goes to tutor
        findTutorButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeStudentFragment_to_findTutorFragment)
        }
        messageTutorButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeStudentFragment_to_studentMessageFragment)
        }
        editButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeStudentFragment_to_studentProfilePrivateFragment)
        }
        settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeStudentFragment_to_studentSettingsFragment)
        }
        return view
    }
}