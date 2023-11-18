package com.example.tutortime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class LaunchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_launch, container, false)
        val loginButton: Button = view.findViewById(R.id.login)
        val register: Button = view.findViewById(R.id.register)
        val login_student: Button = view.findViewById(R.id.login_student)
        // this goes to tutor
        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_launchFragment_to_tutorHomeFragment)
        }
        register.setOnClickListener {
            findNavController().navigate(R.id.action_launchFragment_to_registerFragment)
        }
        login_student.setOnClickListener {
            findNavController().navigate(R.id.action_launchFragment_to_homeStudentFragment)
        }

        return view
    }
}