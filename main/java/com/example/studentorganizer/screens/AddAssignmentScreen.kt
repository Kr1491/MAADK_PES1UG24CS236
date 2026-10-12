package com.example.studentorganizer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentorganizer.components.AppBar
import com.example.studentorganizer.components.AssignmentForm
import com.example.studentorganizer.data.SampleData
import com.example.studentorganizer.model.Assignment

@Composable
fun AddAssignmentScreen(
    assignments: List<Assignment>,
    onSave: (Assignment) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("Low") }
    var description by remember { mutableStateOf("") }
    var reminderEnabled by remember { mutableStateOf(false) }

    val canSave = title.isNotBlank() && course.isNotBlank() && dueDate.isNotBlank()

    Column(modifier = modifier.fillMaxSize()) {
        AppBar(
            title = "Add New Assignment",
            canNavigateBack = true,
            onBackClick = onBack
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                AssignmentForm(
                    title = title,
                    course = course,
                    dueDate = dueDate,
                    priority = priority,
                    description = description,
                    reminderEnabled = reminderEnabled,
                    onTitleChange = { title = it },
                    onCourseChange = { course = it },
                    onDueDateChange = { dueDate = it },
                    onPriorityChange = { priority = it },
                    onDescriptionChange = { description = it },
                    onReminderChange = { reminderEnabled = it },
                    courses = SampleData.courses
                )
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedButton(
                        onClick = onBack,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancel")
                    }
                    Button(
                        onClick = {
                            val newAssignment = Assignment(
                                id = assignments.maxOf { it.id } + 1,
                                title = title,
                                course = course,
                                dueDate = dueDate,
                                priority = priority,
                                isCompleted = false,
                                description = description,
                                reminderEnabled = reminderEnabled
                            )
                            onSave(newAssignment)
                            onBack()
                        },
                        enabled = canSave,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Save Assignment")
                    }
                }
            }
        }
    }
}
