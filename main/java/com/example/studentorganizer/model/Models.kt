package com.example.studentorganizer.model

data class Course(
    val courseName: String,
    val courseCode: String,
    val instructor: String,
    val progress: Float
)

data class Assignment(
    val id: Int,
    val title: String,
    val course: String,
    val dueDate: String,
    val priority: String,
    val isCompleted: Boolean,
    val description: String = "",
    val reminderEnabled: Boolean = false
)

data class Student(
    val name: String,
    val studentId: String,
    val major: String,
    val avatarRes: Int
)
