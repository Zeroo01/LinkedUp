package com.example.linkedup.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linkedup.ui.auth.AuthScreen
import com.example.linkedup.ui.screens.ApplicantScreen
import com.example.linkedup.ui.screens.RecruiterScreen
import com.example.linkedup.ui.screens.EventManagerScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {

        // AUTH SCREEN
        composable("auth") {

            AuthScreen { role ->

                when (role) {

                    "APPLICANT" -> {
                        navController.navigate("applicant") {
                            popUpTo("auth") { inclusive = true }
                        }
                    }

                    "RECRUITER" -> {
                        navController.navigate("recruiter") {
                            popUpTo("auth") { inclusive = true }
                        }
                    }

                    "EVENT_MANAGER" -> {
                        navController.navigate("event") {
                            popUpTo("auth") { inclusive = true }
                        }
                    }
                }
            }
        }

        // APPLICANT
        composable("applicant") {

            ApplicantScreen(
                onLogout = {
                    navController.navigate("auth") {
                        popUpTo(0)
                    }
                }
            )
        }

        // RECRUITER
        composable("recruiter") {

            RecruiterScreen(
                onLogout = {
                    navController.navigate("auth") {
                        popUpTo(0)
                    }
                }
            )
        }

        // EVENT MANAGER
        composable("event") {

            EventManagerScreen(
                onLogout = {
                    navController.navigate("auth") {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}