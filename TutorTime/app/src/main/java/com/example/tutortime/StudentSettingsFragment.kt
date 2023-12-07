package com.example.tutortime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.paging.NullPaddedList
import com.example.tutortime.databinding.FragmentStudentSettingsBinding
import com.example.tutortime.databinding.FragmentTutorSettingsBinding
import com.google.firebase.database.*
import java.util.UUID


class StudentSettingsFragment : Fragment() {
    private lateinit var databaseRef: DatabaseReference
    private val model: UserViewModel by activityViewModels()

    // ViewModel
    private val userModel: UserViewModel by activityViewModels()

    // Binding
    private var _binding: FragmentStudentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment + Binding
        _binding = FragmentStudentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        val save: Button = view.findViewById(R.id.save)
        val budgetarray = resources.getStringArray(R.array.budget)
        val genderarray = resources.getStringArray(R.array.genderPrefer)
        val privacyarray = resources.getStringArray(R.array.privacy)
        val distancearray = resources.getStringArray(R.array.distance)
        val languagearray = resources.getStringArray(R.array.language)
        var budgettext: String? = null
        var gendertext: String? = null
        var privacytext: String? = null
        var distanctext: String? = null
        var languagetext: String? = null
        var settings: Any? = null

        databaseRef.orderByChild("userId").equalTo(model.user_id).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (userSnapshot in snapshot.children) {
                    settings = userSnapshot.child("setting").value
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })

        binding.languageSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                languagetext = languagearray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        binding.privacySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                privacytext = privacyarray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        binding.genderSpineer.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                gendertext = genderarray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        binding.distanceSpineer.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                distanctext = distancearray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        binding.budgetSpineer.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                budgettext = budgetarray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }


        // Init Database
        databaseRef = FirebaseDatabase.getInstance().reference
            .child("Users").child(userModel.getId()!!).child("Settings")

        val studentSettings = mapOf<String, String>(
            "Language" to languagetext!!,
            "Privacy" to privacytext!!,
            "GanderPreference" to gendertext!!,
            "DistanceRange" to distanctext!!,
            "Budget" to budgettext!!)

        addSettings(studentSettings)

        return view
    }

    private fun addSettings(studentSettings: Map<String, String>) {
        databaseRef.updateChildren(studentSettings)
            .addOnCompleteListener {
            }
    }



}