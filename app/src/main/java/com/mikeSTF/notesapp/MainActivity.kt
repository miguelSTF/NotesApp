package com.mikeSTF.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mikeSTF.notesapp.navigation.SetupNavigation
import com.mikeSTF.notesapp.ui.theme.NotesAppTheme
import com.mikeSTF.notesapp.ui.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                sharedViewModel.shouldShowSplashScreen.value
            }
        }

        setContent {
            NotesAppTheme {
                navController = rememberNavController()
                SetupNavigation(navController = navController, sharedViewModel = sharedViewModel)
            }
        }
    }
}