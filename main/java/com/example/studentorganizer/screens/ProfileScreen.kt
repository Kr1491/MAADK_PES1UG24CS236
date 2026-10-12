package com.example.studentorganizer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentorganizer.components.AppBar
import com.example.studentorganizer.components.StudentProfile
import com.example.studentorganizer.model.Student

@Composable
fun ProfileScreen(
    student: Student,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        AppBar(
            title = "Student Profile",
            canNavigateBack = false,
            onBackClick = {}
        )
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StudentProfile(
                studentName = student.name,
                studentId = student.studentId,
                major = student.major,
                avatarRes = student.avatarRes
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Academic Stats", style = MaterialTheme.typography.titleMedium)
                    Text("GPA: 3.82")
                    Text("Completed Credits: 84")
                    Text("Current Semester: Fall 2026")
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = {}, modifier = Modifier.weight(1f)) {
                    Text("Edit Profile")
                }
                Button(onClick = {}, modifier = Modifier.weight(1f)) {
                    Text("Export Academic Summary")
                }
            }
        }
    }
}
