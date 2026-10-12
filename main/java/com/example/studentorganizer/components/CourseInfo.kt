package com.example.studentorganizer.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CourseInfo(
    courseName: String,
    courseCode: String,
    instructor: String,
    progress: Float,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.width(220.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(courseName, style = MaterialTheme.typography.titleMedium)
            Text(courseCode, style = MaterialTheme.typography.bodyMedium)
            Text(instructor, style = MaterialTheme.typography.bodySmall)
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseInfoPreview() {
    CourseInfo(
        courseName = "Mobile App Development",
        courseCode = "CS101",
        instructor = "Prof. Shridevi A. Sawant",
        progress = 0.65f
    )
}
