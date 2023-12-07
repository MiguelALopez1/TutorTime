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


class StudentSettingsFragment : Fragment() {

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

        language.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }





        return view
    }

}