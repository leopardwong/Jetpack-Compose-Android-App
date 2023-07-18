package com.example.jetpack_compose_sample_app.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_compose_sample_app.R
import com.example.jetpack_compose_sample_app.ui.NavigationGraph
import com.example.jetpack_compose_sample_app.ui.custom.BottomBarVisible
import com.example.jetpack_compose_sample_app.ui.custom.TopBarVisible
import com.example.jetpack_compose_sample_app.ui.viewmodel.MainScreenViewModel

@Composable
fun MainScreenView(context: Context){
    var title by remember { mutableStateOf("") }
    val barState = rememberSaveable { (mutableStateOf(true)) }
    val navController = rememberNavController()
    val mainScreenViewModel = MainScreenViewModel(context)
    Scaffold(
        topBar = { TopBarVisible(
            title = title,
            topBarState = barState,
            context = context,
        ) },
        bottomBar = { BottomBarVisible(
            navController = navController,
            bottomBarState = barState,
            mainScreenViewModel = mainScreenViewModel) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(
                navController = navController,
                context = context,
                mainScreenViewModel = mainScreenViewModel
            )
        }
        LaunchedEffect(navController){
            navController.currentBackStackEntryFlow.collect{backStackEntry ->
                val currentRoute = backStackEntry.destination.route.toString()
                when (currentRoute){
                    "home" -> title = context.getString(R.string.home)
                    "portfolio" -> title = context.getString(R.string.portfolio)
                    "for_you" -> title = context.getString(R.string.for_you)
                    "setting" -> title = context.getString(R.string.setting)
                }
//                when(currentRoute){
//                    "login" -> barState.value = false
//                    else -> barState.value = true
//                }
            }
        }
    }
}