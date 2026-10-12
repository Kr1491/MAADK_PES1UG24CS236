package com.example.studentorganizer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentorganizer.components.AppBar
import com.example.studentorganizer.components.AssignmentCard
import com.example.studentorganizer.model.Assignment

@Composable
fun AssignmentsScreen(
    assignments: List<Assignment>,
    onToggleComplete: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var filter by remember { mutableStateOf("All") }

    val visible = when (filter) {
        "Pending" -> assignments.filter { !it.isCompleted }
        "Completed" -> assignments.filter { it.isCompleted }
        else -> assignments
    }

    Column(modifier = modifier.fillMaxSize()) {
        AppBar(
            title = "My Assignments",
            canNavigateBack = false,
            onBackClick = {}
        )
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf("All", "Pending", "Completed").forEach { option ->
                RadioButton(
                    selected = filter == option,
                    onClick = { filter = option }
                )
                Text(option)
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 88.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(visible, key = { it.id }) { item ->
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
