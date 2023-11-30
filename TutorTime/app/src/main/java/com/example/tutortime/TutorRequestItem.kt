package com.example.tutortime

data class TutorRequestItem(var profilePic: String,
                            var name: String, var subject: String,
                            var day: String, val startTime: String,
                            var endTime: String)
