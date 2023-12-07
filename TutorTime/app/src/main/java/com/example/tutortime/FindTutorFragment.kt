package com.example.tutortime

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutortime.databinding.FragmentFindTutorBinding
import com.google.firebase.database.*

class FindTutorFragment : Fragment() {

    // Binding
    private var _binding: FragmentFindTutorBinding? = null
    private val binding get() = _binding!!
    // Adapter
    private lateinit var adapter: FindTutorsAdapter
    // ViewModel
    private val userModel: UserViewModel by activityViewModels()

    // Database
    private lateinit var databaseRef: DatabaseReference
    var tutors = mutableListOf<FindTutorItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment + Binding
        _binding = FragmentFindTutorBinding.inflate(inflater, container, false)
        val view = binding.root

        // RecyclerView Setup
        val recyclerView = binding.tutorlist
        adapter = FindTutorsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        databaseRef = FirebaseDatabase.getInstance().reference
            .child("Users").child(userModel.getId()!!).child("Tutors")

        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                tutors.clear()
                for (tutorSnapshot in snapshot.children) {
                    val tutor = tutorSnapshot.key?.let{
                        FindTutorItem(tutorSnapshot.child("profilePic").value.toString(),
                            tutorSnapshot.child("background").value.toString(),
                            tutorSnapshot.child("info").value.toString())
                    }

                    if (tutor != null) {
                        tutors.add(tutor)
                    }
                }
                adapter.setTutors(tutors)
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })



        return view
    }


    /**
     * The adapter for the RecyclerView. Provides the list of items to be displayed there.
     */
    inner class FindTutorsAdapter :
        RecyclerView.Adapter<FindTutorsAdapter.TutorViewHolder>() {

        //a list of the movie items to load into the RecyclerView
        private var tutors = emptyList<FindTutorItem>()

        internal fun setTutors(tutors: List<FindTutorItem>) {
            this.tutors = tutors
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return tutors.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerviewfindtutor, parent, false)
            return TutorViewHolder(v)
        }

        override fun onBindViewHolder(holder: TutorViewHolder, position: Int) {

            // Add the profile Image
//            Glide.with(holder.view.context)
//                .load(holder.view.context.getString(R.string.?????) + tutors[position].profilePic //possible path extension
//                .apply(RequestOptions().override(128, 128))
//                .into(holder.view.findViewById(R.id.poster))

            // Add background information
            holder.view.findViewById<TextView>(R.id.background).text = tutors[position].background

            // Add other information
            holder.view.findViewById<TextView>(R.id.Information).text = tutors[position].info

            // When ViewHolder is pressed
            holder.itemView.setOnClickListener {
                // Opens the profile fragment
                holder.view.findNavController().navigate(R.id.action_findTutorFragment_to_studentProfilePublicFragment)
            }
        }

        inner class TutorViewHolder(val view: View) : RecyclerView.ViewHolder(view),
            View.OnClickListener {
            override fun onClick(view: View?) {
                if (view != null) {

                }
            }
        }

    }
}