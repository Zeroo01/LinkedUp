package com.example.linkedup.ui.screens

import androidx.navigation.NavHostController

object RoleNavigator {

    fun navigateByRole(
        role: String,
        navController: NavHostController
    ) {
        when (role) {

            "APPLICANT" ->
                navController.navigate(Routes.ApplicantHome)

            "RECRUITER" ->
                navController.navigate(Routes.RecruiterHome)

            "EVENT_MANAGER" ->
                navController.navigate(Routes.EventManagerHome)
        }
    }
}