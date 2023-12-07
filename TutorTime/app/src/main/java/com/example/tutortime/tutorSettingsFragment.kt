package com.example.tutortime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.tutortime.databinding.FragmentFindTutorBinding
import com.example.tutortime.databinding.FragmentTutorSettingsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class tutorSettingsFragment : Fragment() {

    // Add Database
    private lateinit var databaseRef: DatabaseReference

    // ViewModel
    private val userModel: UserViewModel by activityViewModels()

    // Binding
    private var _binding: FragmentTutorSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment + Binding
        _binding = FragmentTutorSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        // Init Database
        databaseRef = FirebaseDatabase.getInstance().reference
            .child("Users").child(userModel.getId()!!).child("Settings")

        val settings = mapOf<String, String>(
            "Language" to binding.language.toString(),
            "Status" to binding.status.toString(),
            "DistanceRange" to binding.distanceRange.toString(),
            "Price" to binding.price.toString(),
            "Subject" to binding.subject.toString())

        addSettings(settings)

        return view
    }

    private fun addSettings(tutorSettings: Map<String, String>) {
        databaseRef.updateChildren(tutorSettings)
            .addOnCompleteListener {
            }

    }

}