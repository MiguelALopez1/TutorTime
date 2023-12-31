package com.example.tutortime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutortime.databinding.FragmentStudentMessageBinding
import com.google.firebase.database.*


/**
 * A simple [Fragment] subclass.
 * Use the [StudentMessageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentMessageFragment : Fragment() {

    // Binding
    private var _binding: FragmentStudentMessageBinding? = null
    private val binding get() = _binding!!
    // Adapter
    private lateinit var adapter: StudentMessageFragment.MessagesAdapter
    // ViewModel
    private val userModel: UserViewModel by activityViewModels()

    // Database
    private lateinit var databaseRef: DatabaseReference
    var messages = mutableListOf<MessageItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment + Binding
        _binding = FragmentStudentMessageBinding.inflate(inflater, container, false)
        val view = binding.root

        // RecyclerView Setup
        val recyclerView = binding.recyclerViewMessages
        adapter = MessagesAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        databaseRef = FirebaseDatabase.getInstance().reference
            .child("Users").child(userModel.getId()!!).child("Messages")

        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                messages.clear()
                for (messageSnapshot in snapshot.children) {
                    val message = messageSnapshot.key?.let{
                        MessageItem(messageSnapshot.child("subject").value.toString(),
                            messageSnapshot.child("preview").value.toString())
                    }

                    if (message != null) {
                        messages.add(message)
                    }
                }
                adapter.setMessages(messages)
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
    inner class MessagesAdapter :
        RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {

        //a list of the movie items to load into the RecyclerView
        private var messages = emptyList<MessageItem>()

        internal fun setMessages(message: List<MessageItem>) {
            this.messages = message
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return messages.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerviewfindtutor, parent, false)
            return MessageViewHolder(v)
        }

        override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {

            // Add subject
            holder.view.findViewById<TextView>(R.id.textViewSubject).text = messages[position].subject

            // Add message preview
            holder.view.findViewById<TextView>(R.id.textViewMessage).text = messages[position].preview

            // When ViewHolder is pressed
            holder.itemView.setOnClickListener {

                //holder.view.findNavController().navigate(R.id.action_??)
            }
        }

        inner class MessageViewHolder(val view: View) : RecyclerView.ViewHolder(view),
            View.OnClickListener {
            override fun onClick(view: View?) {
                if (view != null) {

                }
            }
        }

    }
}


