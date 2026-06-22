package com.example.linkedup.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linkedup.data.AuthRepository
import com.example.linkedup.ui.auth.AuthScreen
import com.example.linkedup.ui.screens.Applicant.ApplicantHomeScreen
import com.example.linkedup.ui.screens.Recruiter.RecruiterHomeScreen
import com.example.linkedup.ui.screens.RoleNavigator
import com.example.linkedup.ui.screens.Routes
import com.example.linkedup.ui.screens.eventmanager.EventManagerHomeScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Routes.Auth
    ) {


        // LOGIN / REGISTER
        composable(Routes.Auth) {

            AuthScreen { role ->

                RoleNavigator.navigateByRole(
                    role = role,
                    navController = navController
                )
            }
        }


        // BEWERBER
        composable(Routes.ApplicantHome) {

            ApplicantHomeScreen(
                onLogout = {

                    AuthRepository.logout()

                    navController.navigate(Routes.Auth) {

                        popUpTo(0) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            )
        }


        // EVENT MANAGER
        composable(Routes.EventManagerHome) {

            EventManagerHomeScreen(
                onLogout = {

                    AuthRepository.logout()

                    navController.navigate(Routes.Auth) {

                        popUpTo(0) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            )
        }


        // RECRUITER
        composable(Routes.RecruiterHome) {

            RecruiterHomeScreen(
                onLogout = {

                    AuthRepository.logout()

                    navController.navigate(Routes.Auth) {

                        popUpTo(0) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            )
        }
    }
}