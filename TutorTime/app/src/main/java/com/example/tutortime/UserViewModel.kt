package com.example.tutortime

import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var user_id :String? = null


    fun setId(user_id: String) {
        this.user_id = user_id
    }
    fun getId(): String? {
        return this.user_id
    }
}