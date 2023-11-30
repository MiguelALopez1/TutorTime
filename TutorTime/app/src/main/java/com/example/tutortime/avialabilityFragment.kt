package com.example.tutortime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutortime.databinding.FragmentAvialabilityBinding
import com.example.tutortime.databinding.FragmentFindTutorBinding

/**
 * A simple [Fragment] subclass.
 * Use the [avialabilityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class avialabilityFragment : Fragment() {

    // Binding
    private var _binding: FragmentAvialabilityBinding? = null
    private val binding get() = _binding!!
    // Adapter
    private lateinit var adapter: avialabilityFragment.AvailabilityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment + Binding
        _binding = FragmentAvialabilityBinding.inflate(inflater, container, false)
        val view = binding.root

        // RecyclerView Setup
        val recyclerView = binding.recyclerViewAvailability
        adapter = AvailabilityAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


        return view
    }

    /**
     * The adapter for the RecyclerView. Provides the list of items to be displayed there.
     */
    inner class AvailabilityAdapter :
        RecyclerView.Adapter<AvailabilityAdapter.AvailabilityViewHolder>() {

        //a list of the movie items to load into the RecyclerView
        private var availabilities = emptyList<AvailabilityItem>()

        internal fun setAvailabilities(availability: List<AvailabilityItem>) {
            this.availabilities = availability
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return availabilities.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailabilityViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerviewfindtutor, parent, false)
            return AvailabilityViewHolder(v)
        }

        override fun onBindViewHolder(holder: AvailabilityViewHolder, position: Int) {

            // Add day
            holder.view.findViewById<TextView>(R.id.textViewDay).text = availabilities[position].day

            // Add button info
            holder.view.findViewById<TextView>(R.id.buttonTimeSlot).text =
                availabilities[position].startTime + " - " + availabilities[position].endTime

            // When ViewHolder is pressed
            holder.itemView.setOnClickListener {
                // Opens the profile fragment
                holder.view.findNavController().navigate(R.id.action_avialabilityFragment_to_requestFragment)
            }
        }

        inner class AvailabilityViewHolder(val view: View) : RecyclerView.ViewHolder(view),
            View.OnClickListener {
            override fun onClick(view: View?) {
                if (view != null) {

                }
            }
        }

    }
}