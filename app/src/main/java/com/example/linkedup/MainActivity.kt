package com.example.linkedup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.linkedup.navigation.AppNavGraph
import com.example.linkedup.ui.theme.LinkedUpTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LinkedUpTheme {
                AppNavGraph()
            }
        }
    }
}