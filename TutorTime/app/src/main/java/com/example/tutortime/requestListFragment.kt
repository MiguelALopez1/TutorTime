package com.example.tutortime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutortime.databinding.FragmentRequestListBinding


class requestListFragment : Fragment() {

    // Binding
    private var _binding: FragmentRequestListBinding? = null
    private val binding get() = _binding!!
    // Adapter
    private lateinit var adapter: requestListFragment.RequestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment + Binding
        _binding = FragmentRequestListBinding.inflate(inflater, container, false)
        val view = binding.root

        // RecyclerView Setup
        val recyclerView = binding.recyclerViewRequests
        adapter = RequestAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        return view
    }


    /**
     * The adapter for the RecyclerView. Provides the list of items to be displayed there.
     */
    inner class RequestAdapter :
        RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {

        //a list of the movie items to load into the RecyclerView
        private var requests = emptyList<TutorRequestItem>()

        internal fun setRequests(request: List<TutorRequestItem>) {
            this.requests = request
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return requests.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerviewfindtutor, parent, false)
            return RequestViewHolder(v)
        }

        override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {

            // Add name
            holder.view.findViewById<TextView>(R.id.textViewStudentName).text = requests[position].name

            // Add info
            holder.view.findViewById<TextView>(R.id.textViewDetails).text =
                requests[position].subject + "\n" + requests[position].day + " " +
                requests[position].startTime + " - " + requests[position].endTime

            // When ViewHolder is pressed
            holder.itemView.setOnClickListener {
                // Opens the profile fragment
                holder.view.findNavController().navigate(R.id.action_requestListFragment_to_tutorProfilePublicFragment)
            }
        }

        inner class RequestViewHolder(val view: View) : RecyclerView.ViewHolder(view),
            View.OnClickListener {
            override fun onClick(view: View?) {
                if (view != null) {

                }
            }
        }

    }

}