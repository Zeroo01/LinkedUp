package com.example.linkedup.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


data class BottomItem(
    val title: String
)


@Composable
fun RoleNavBar(
    items: List<BottomItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {

    NavigationBar {

        items.forEachIndexed { index, item ->

            NavigationBarItem(

                selected = selectedIndex == index,

                onClick = {
                    onItemSelected(index)
                },

                label = {
                    Text(item.title)
                },

                icon = {}
            )
        }
    }
}