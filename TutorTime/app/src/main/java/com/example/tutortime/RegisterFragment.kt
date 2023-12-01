package com.example.tutortime

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

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
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val createAccountButton: Button = view.findViewById(R.id.createAccount)
        createAccountButton.setOnClickListener { createAccount(view) }

        return view
    }

    private fun createAccount(view: View) {
        val emailEditText: EditText = view.findViewById(R.id.email)
        val passwordEditText: EditText = view.findViewById(R.id.password)
        val confrimPasswordEditText: EditText = view.findViewById(R.id.confirmPassword)

        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val comfrim_password = confrimPasswordEditText.text.toString().trim()

        // Validate inputs
        if (email.isEmpty() || password.isEmpty() || comfrim_password.isEmpty()) {
            Toast.makeText(context, "Email, password, or comfrim password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        // Validate the password and confrim Password
        if(password != comfrim_password){
            Toast.makeText(context, "Comfrim Password is not correct", Toast.LENGTH_SHORT).show()
            return
        }
        // Create a new user with Firebase Authentication
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("RegisterFragment", "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("RegisterFragment", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            findNavController().navigate(R.id.launchFragment)
        } else {
            Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
        }
    }
}
