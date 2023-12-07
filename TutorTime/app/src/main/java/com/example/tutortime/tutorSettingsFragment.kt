package com.example.tutortime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import com.example.tutortime.databinding.FragmentFindTutorBinding
import com.example.tutortime.databinding.FragmentTutorSettingsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.Button
import android.widget.Spinner

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

        val save: Button = view.findViewById(R.id.save)
        val statusarray = resources.getStringArray(R.array.status)
        val pricearray = resources.getStringArray(R.array.price)
        val subjectarray = resources.getStringArray(R.array.subjects)
        val distancearray = resources.getStringArray(R.array.distance)
        val languagearray = resources.getStringArray(R.array.language)
        var statusttext: String? = null
        var pricetext: String? = null
        var subjecttext: String? = null
        var distanctext: String? = null
        var languagetext: String? = null

        binding.languageSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                languagetext = languagearray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        binding.statusSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                statusttext = statusarray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        binding.priceSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                pricetext = pricearray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        binding.subjectSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                subjecttext = subjectarray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        binding.distanceRangeSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                distanctext = distancearray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        // Init Database
        databaseRef = FirebaseDatabase.getInstance().reference
            .child("Users").child(userModel.getId()!!).child("Settings")

        binding.save.setOnClickListener {
            val tutorsetting = mapOf<String, String>(
                "Language" to languagetext!!,
                "Status" to statusttext!!,
                "DistanceRange" to distanctext!!,
                "Price" to pricetext!!,
                "Subject" to subjecttext!!)
            addSettings(tutorsetting)
        }
        return view
    }

    private fun addSettings(tutorSettings: Map<String, String>) {
        databaseRef.updateChildren(tutorSettings)
            .addOnCompleteListener {
            }

    }

}