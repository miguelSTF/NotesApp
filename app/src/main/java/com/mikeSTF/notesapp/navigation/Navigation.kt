package com.mikeSTF.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mikeSTF.notesapp.navigation.destinations.listComposable
import com.mikeSTF.notesapp.navigation.destinations.noteComposable
import com.mikeSTF.notesapp.navigation.destinations.splashComposable
import com.mikeSTF.notesapp.ui.viewmodels.SharedViewModel
import com.mikeSTF.notesapp.utils.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val screenRoutes = remember(navController) {
        ScreenRoutes(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        splashComposable(
            navigateToListScreen = screenRoutes.fromSplashToList
        )
        listComposable(
            navigateToNoteScreen = screenRoutes.fromListToNote,
            sharedViewModel = sharedViewModel
        )
        noteComposable(
            navigateToListScreen = screenRoutes.fromNoteToList,
            sharedViewModel = sharedViewModel
        )
    }
}