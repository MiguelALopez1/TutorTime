package com.example.tutortime

data class UserItem(var name: String, var userId: String, var student: Boolean,
                    var studentSettings: StudentSettings?=null, var tutorSettings: TutorSettings?=null)
