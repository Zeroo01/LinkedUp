package com.example.linkedup.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.linkedup.ui.auth.AuthScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Routes.Auth) {
            AuthScreen(
                onLoginSuccess = { role ->
                    RoleNavigator.navigateByRole(role, navController)
                }
            )
        }

//        composable(Routes.ApplicantHome) {
//            ApplicantScreen()
//        }

//        composable(Routes.RecruiterHome) {
//            RecruiterScreen()
//        }

        composable(Routes.EventManagerHome) {
            EventManagerScreen(
                viewModel = TODO(),
                onLogout = TODO()
            )
        }

//        composable(Routes.EventCreate) {
//            EventCreateScreen(...) //TODO eventcreatescreen
//        }

        composable(Routes.EventDetail) {
            EventDetailScreen(
                event = TODO(),
                onBack = TODO()
            )
        }
    }
}