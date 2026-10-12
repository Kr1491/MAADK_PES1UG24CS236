package com.example.studentorganizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studentorganizer.components.BottomBar
import com.example.studentorganizer.data.SampleData
import com.example.studentorganizer.navigation.Screen
import com.example.studentorganizer.navigation.StudentNavHost
import com.example.studentorganizer.ui.theme.StudentOrganizerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentApp()
        }
    }
}

@Composable
fun StudentApp() {
    var assignments by remember { mutableStateOf(SampleData.assignments) }
    var darkMode by rememberSaveable { mutableStateOf(false) }
    var notificationsOn by rememberSaveable { mutableStateOf(true) }
    var startScreen by rememberSaveable { mutableStateOf(Screen.Dashboard.route) }

    // Read once, so changing the radio in Settings applies the next time the activity is created.
    val startDestination = remember { startScreen }

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // The theme lives here (not in MainActivity) because the dark mode state is owned here.
    StudentOrganizerTheme(darkTheme = darkMode, dynamicColor = false) {
        Scaffold(
            bottomBar = {
                if (currentRoute != Screen.AddAssignment.route) {
                    BottomBar(navController = navController, currentRoute = currentRoute)
                }
            },
            floatingActionButton = {
                if (currentRoute == Screen.Dashboard.route || currentRoute == Screen.Assignments.route) {
                    FloatingActionButton(onClick = {
                        navController.navigate(Screen.AddAssignment.route)
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add assignment")
                    }
                }
            }
        ) { innerPadding ->
            StudentNavHost(
                navController = navController,
                startDestination = startDestination,
                assignments = assignments,
                onToggleComplete = { id ->
                    assignments = assignments.map { item ->
                        if (item.id == id) item.copy(isCompleted = !item.isCompleted) else item
                    }
                },
                onSaveAssignment = { newAssignment ->
                    assignments = assignments + newAssignment
                },
                darkMode = darkMode,
                onDarkModeChange = { darkMode = it },
                notificationsOn = notificationsOn,
                onNotificationsChange = { notificationsOn = it },
                startScreen = startScreen,
                onStartScreenChange = { startScreen = it },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
