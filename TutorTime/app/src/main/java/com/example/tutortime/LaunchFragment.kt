package com.example.tutortime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LaunchFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    // ...
    // Initialize Firebase Auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_launch, container, false)

        val loginButton: Button = view.findViewById(R.id.login)
        val registerButton: Button = view.findViewById(R.id.register)
        val loginStudentButton: Button = view.findViewById(R.id.login_student)
        val usernameEditText: EditText = view.findViewById(R.id.username)
        val passwordEditText: EditText = view.findViewById(R.id.password)

        loginButton.setOnClickListener {
            signInUser(usernameEditText.text.toString(), passwordEditText.text.toString())
        }

        registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_launchFragment_to_registerFragment)
        }

        loginStudentButton.setOnClickListener {
            findNavController().navigate(R.id.action_launchFragment_to_homeStudentFragment)
        }

        return view
    }

    private fun signInUser(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                updateUI(auth.currentUser)
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(context, "Username or Password is not exist. Please try again: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // User is signed in
            findNavController().navigate(R.id.homeStudentFragment)
        }
    }

}