package com.example.jetpack_compose_sample_app.ui

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.jetpack_compose_sample_app.MainActivity
import com.example.jetpack_compose_sample_app.helper.SharedPreferenceHelper
import com.example.jetpack_compose_sample_app.ui.screen.HomeScreen
import com.example.jetpack_compose_sample_app.ui.viewmodel.MainScreenViewModel

@Composable
fun NavigationGraph(navController: NavHostController,context: Context,mainScreenViewModel: MainScreenViewModel) {
    NavHost(
        navController,
        startDestination = "main"
    ) {
        navigation(startDestination = "home", route = "main"){
            mainScreenViewModel.bottomNavItems.forEach {
                when(it.screenRoute){
                    "home" -> composable(it.screenRoute) { HomeScreen(context) }
                    "portfolio" -> composable(it.screenRoute) { PortfolioScreen(context) }
                    "for_you" -> composable(it.screenRoute) { ForYouScreen(context) }
                    "setting" -> composable(it.screenRoute) {
                        SettingScreen(onNavigateToOtherPage = {
                            val intent = Intent(context, MainActivity::class.java)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            SharedPreferenceHelper(context).save("isLoggedIn",false)
                            context.startActivity(intent)
                        })
                    }
                }
            }
        }
    }
}


