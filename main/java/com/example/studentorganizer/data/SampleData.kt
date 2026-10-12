package com.example.studentorganizer.data

import com.example.studentorganizer.R
import com.example.studentorganizer.model.Assignment
import com.example.studentorganizer.model.Course
import com.example.studentorganizer.model.Student

object SampleData {
    // TODO: change the name and id before you submit
    val student = Student(
        name = "John",
        studentId = "PES1UG24CS000",
        major = "Computer Science",
        avatarRes = R.drawable.avatar
    )

    val courses = listOf(
        Course("Mobile App Development", "CS101", "Prof. Shridevi A. Sawant", 0.65f),
        Course("Discrete Mathematics", "MATH201", "John", 0.40f),
        Course("Technical English", "ENG102", "Priya", 0.80f),
        Course("Physics Lab", "PHY110", "Rahul", 0.25f)
    )

    val assignments = listOf(
        Assignment(1, "Kotlin functions worksheet", "CS101", "12 Oct 2026", "High", false),
        Assignment(2, "Compose navigation exercise", "CS101", "14 Oct 2026", "High", false),
        Assignment(3, "Set theory problem set", "MATH201", "16 Oct 2026", "Medium", false),
        Assignment(4, "Essay draft", "ENG102", "18 Oct 2026", "Low", false),
        Assignment(5, "Lab report 3", "PHY110", "20 Oct 2026", "Medium", false),
        Assignment(6, "Database worksheet", "CS210", "22 Oct 2026", "High", false),
        Assignment(7, "Group presentation slides", "ENG102", "25 Oct 2026", "Medium", false),
        Assignment(8, "Quiz revision", "MATH201", "08 Oct 2026", "Low", true),
        Assignment(9, "Reading response", "ENG102", "05 Oct 2026", "Low", true)
    )
}
