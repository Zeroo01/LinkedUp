package com.example.linkedup.ui.screens.Recruiter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.linkedup.ui.components.BottomItem
import com.example.linkedup.ui.components.RoleNavBar

@Composable
fun RecruiterHomeScreen(
    onLogout: () -> Unit
) {


    var selectedTab by remember {
        mutableStateOf(0)
    }


    var showCreateJob by remember {
        mutableStateOf(false)
    }



    Scaffold(

        bottomBar = {


            if(!showCreateJob) {


                RoleNavBar(

                    items = listOf(

                        BottomItem("Home"),

                        BottomItem("Matches"),

                        BottomItem("Stellen")

                    ),


                    selectedIndex = selectedTab,


                    onItemSelected = {

                        selectedTab = it

                    }

                )

            }

        }

    ) { padding ->



        Box(
            modifier = Modifier.padding(padding)
        ) {



            if(showCreateJob) {


                CreateJobScreen(

                    onBack = {

                        showCreateJob = false

                    }

                )


            }

            else {


                when(selectedTab) {


                    0 -> RecruiterDashboard(
                        onLogout = onLogout
                    )


                    1 -> RecruiterMatchesScreen()


                    2 -> RecruiterJobsScreen(

                        onCreateJob = {

                            showCreateJob = true

                        }

                    )

                }


            }



        }


    }

}