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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import java.util.UUID


class StudentSettingsFragment : Fragment() {
    private lateinit var databaseRef: DatabaseReference
    private val model: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_student_settings, container, false)
        val language: Spinner = view.findViewById(R.id.languageSpinner)
        val privacy: Spinner = view.findViewById(R.id.privacySpinner)
        val gender: Spinner = view.findViewById(R.id.genderSpineer)
        val distance: Spinner = view.findViewById(R.id.genderSpineer)
        val budget: Spinner = view.findViewById(R.id.budgetSpineer)
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




        language.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                languagetext = languagearray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        privacy.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                privacytext = privacyarray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        gender.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                gendertext = genderarray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        distance.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                distanctext = distancearray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        budget.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                budgettext = budgetarray.getOrNull(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }


        return view
    }

    fun addNewLocation(latitude: Double?,  longitude: Double?, address: String?, timestamp: String?) {

    }



}