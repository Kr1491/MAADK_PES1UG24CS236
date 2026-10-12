package com.example.studentorganizer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.studentorganizer.data.SampleData
import com.example.studentorganizer.model.Assignment
import com.example.studentorganizer.screens.AddAssignmentScreen
import com.example.studentorganizer.screens.AssignmentsScreen
import com.example.studentorganizer.screens.DashboardScreen
import com.example.studentorganizer.screens.ProfileScreen
import com.example.studentorganizer.screens.SettingsScreen

@Composable
fun StudentNavHost(
    navController: NavHostController,
    startDestination: String,
    assignments: List<Assignment>,
    onToggleComplete: (Int) -> Unit,
    onSaveAssignment: (Assignment) -> Unit,
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    notificationsOn: Boolean,
    onNotificationsChange: (Boolean) -> Unit,
    startScreen: String,
    onStartScreenChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                student = SampleData.student,
                courses = SampleData.courses,
                assignments = assignments,
                onToggleComplete = onToggleComplete
            )
        }
        composable(Screen.Assignments.route) {
            AssignmentsScreen(
                assignments = assignments,
                onToggleComplete = onToggleComplete
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(student = SampleData.student)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                darkMode = darkMode,
                onDarkModeChange = onDarkModeChange,
                notificationsOn = notificationsOn,
                onNotificationsChange = onNotificationsChange,
                startScreen = startScreen,
                onStartScreenChange = onStartScreenChange
            )
        }
        composable(Screen.AddAssignment.route) {
            AddAssignmentScreen(
                assignments = assignments,
                onSave = onSaveAssignment,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
