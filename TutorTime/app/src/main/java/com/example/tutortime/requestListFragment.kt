package com.example.tutortime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView


class requestListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_request_list, container, false)
        val list_student: RecyclerView = view.findViewById(R.id.recyclerViewRequests)

        list_student.setOnClickListener {
            findNavController().navigate(R.id.action_requestListFragment_to_tutorProfilePublicFragment)
        }

        return view
    }

}