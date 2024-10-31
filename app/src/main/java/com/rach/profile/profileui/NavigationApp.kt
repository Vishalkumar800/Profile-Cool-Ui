package com.rach.profile.profileui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyAppNavigation(navHostController: NavHostController , viewModel: TopViewModel){

    NavHost(navController = navHostController , startDestination = "Home"){
        composable("Home"){
            ProfileDesign(viewModel = viewModel, navController = navHostController)
        }

        composable("Edit"){
            OnEditOptions(navController = navHostController, viewModel = viewModel)
        }
    }

}