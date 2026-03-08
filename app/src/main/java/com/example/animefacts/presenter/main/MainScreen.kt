package com.example.animefacts.presenter.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.animefacts.presenter.bottomBar.BottomBar

@Composable
fun MainScreen(){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) {paddingValues ->
        MainNavigation(
            navController = navController,
            paddingValues = paddingValues
        )
    }
}