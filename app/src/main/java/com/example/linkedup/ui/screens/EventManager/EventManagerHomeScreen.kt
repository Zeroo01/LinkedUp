package com.example.linkedup.ui.screens.eventmanager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.linkedup.ui.components.BottomItem
import com.example.linkedup.ui.components.RoleNavBar


@Composable
fun EventManagerHomeScreen(
    onLogout: () -> Unit
) {

    var selectedTab by remember {
        mutableStateOf(0)
    }


    Scaffold(

        bottomBar = {

            RoleNavBar(

                items = listOf(

                    BottomItem("Home"),

                    BottomItem("Aktuelle Events"),

                    BottomItem("Events erstellen")
                ),


                selectedIndex = selectedTab,


                onItemSelected = {
                    selectedTab = it
                }
            )
        }

    ) { padding ->


        Box(
            modifier = Modifier.padding(padding)
        ) {


            when(selectedTab) {

                0 -> EventManagerDashboard(
                    onLogout
                )

                1 -> CurrentEventsScreen()


                2 -> CreateEventScreen()
            }
        }
    }
}