package com.example.studentorganizer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentorganizer.components.AppBar
import com.example.studentorganizer.navigation.Screen

@Composable
fun SettingsScreen(
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    notificationsOn: Boolean,
    onNotificationsChange: (Boolean) -> Unit,
    startScreen: String,
    onStartScreenChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        AppBar(
            title = "Settings",
            canNavigateBack = false,
            onBackClick = {}
        )
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Dark Mode")
                Switch(checked = darkMode, onCheckedChange = onDarkModeChange)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Push Notifications")
                Checkbox(checked = notificationsOn, onCheckedChange = onNotificationsChange)
            }

            Text("Default Start Screen")
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = startScreen == Screen.Dashboard.route,
                    onClick = { onStartScreenChange(Screen.Dashboard.route) }
                )
                Text("Dashboard")
                RadioButton(
                    selected = startScreen == Screen.Assignments.route,
                    onClick = { onStartScreenChange(Screen.Assignments.route) }
                )
                Text("Assignments")
            }
        }
    }
}
