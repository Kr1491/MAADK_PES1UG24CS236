package com.example.studentorganizer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentorganizer.components.AppBar
import com.example.studentorganizer.components.AssignmentCard
import com.example.studentorganizer.components.CourseInfo
import com.example.studentorganizer.model.Assignment
import com.example.studentorganizer.model.Course
import com.example.studentorganizer.model.Student

@Composable
fun DashboardScreen(
    student: Student,
    courses: List<Course>,
    assignments: List<Assignment>,
    onToggleComplete: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val pendingCount = assignments.count { !it.isCompleted }
    val urgent = assignments.filter { !it.isCompleted }.take(2)

    Column(modifier = modifier.fillMaxSize()) {
        AppBar(
            title = "Welcome back, ${student.name}!",
            canNavigateBack = false,
            onBackClick = {}
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ElevatedCard(
                    modifier = Modifier.weight(1f),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Active Courses")
                        Text("${courses.size}", style = MaterialTheme.typography.headlineMedium)
                    }
                }
                ElevatedCard(
                    modifier = Modifier.weight(1f),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Pending Tasks")
                        Text("$pendingCount", style = MaterialTheme.typography.headlineMedium)
                    }
                }
            }

            Text(
                "Active Courses",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(courses, key = { it.courseCode }) { course ->
                    CourseInfo(
                        courseName = course.courseName,
                        courseCode = course.courseCode,
                        instructor = course.instructor,
                        progress = course.progress
                    )
                }
            }

            Text(
                "Urgent Deadlines",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                urgent.forEach { item ->
                    AssignmentCard(
                        title = item.title,
                        course = item.course,
                        dueDate = item.dueDate,
                        priority = item.priority,
                        isCompleted = item.isCompleted,
                        onToggleComplete = { onToggleComplete(item.id) }
                    )
                }
            }
        }
    }
}
