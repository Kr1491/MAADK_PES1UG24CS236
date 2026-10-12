package com.example.studentorganizer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studentorganizer.model.Course

@Composable
fun AssignmentForm(
    title: String,
    course: String,
    dueDate: String,
    priority: String,
    description: String,
    reminderEnabled: Boolean,
    onTitleChange: (String) -> Unit,
    onCourseChange: (String) -> Unit,
    onDueDateChange: (String) -> Unit,
    onPriorityChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onReminderChange: (Boolean) -> Unit,
    courses: List<Course>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text("Assignment title") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Box {
            OutlinedTextField(
                value = course,
                onValueChange = {},
                readOnly = true,
                label = { Text("Course") },
                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Select course")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                courses.forEach { item ->
                    DropdownMenuItem(
                        text = { Text("${item.courseCode} - ${item.courseName}") },
                        onClick = {
                            onCourseChange(item.courseCode)
                            expanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = dueDate,
            onValueChange = onDueDateChange,
            label = { Text("Due date") },
            placeholder = { Text("e.g. 30 Oct 2026") },
            singleLine = true,
            trailingIcon = {
                Icon(Icons.Default.DateRange, contentDescription = "Calendar")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Priority")
        Row(verticalAlignment = Alignment.CenterVertically) {
            listOf("Low", "Medium", "High").forEach { option ->
                RadioButton(
                    selected = priority == option,
                    onClick = { onPriorityChange(option) }
                )
                Text(option)
            }
        }

        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            label = { Text("Description") },
            minLines = 3,
            maxLines = 5,
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = reminderEnabled,
                onCheckedChange = onReminderChange
            )
            Text("Enable reminder notification")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AssignmentFormPreview() {
    AssignmentForm(
        title = "Essay draft",
        course = "ENG102",
        dueDate = "18 Oct 2026",
        priority = "Low",
        description = "",
        reminderEnabled = false,
        onTitleChange = {},
        onCourseChange = {},
        onDueDateChange = {},
        onPriorityChange = {},
        onDescriptionChange = {},
        onReminderChange = {},
        courses = listOf(Course("Technical English", "ENG102", "Priya", 0.80f))
    )
}
