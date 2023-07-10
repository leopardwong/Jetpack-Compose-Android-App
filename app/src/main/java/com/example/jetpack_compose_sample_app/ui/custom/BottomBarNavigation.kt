package com.example.jetpack_compose_sample_app.ui.custom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpack_compose_sample_app.ui.theme.BottomBarColor
import com.example.jetpack_compose_sample_app.ui.viewmodel.MainScreenViewModel


@Composable
fun BottomBarVisible(navController: NavController, bottomBarState: MutableState<Boolean>,mainScreenViewModel: MainScreenViewModel){
    AnimatedVisibility(visible = bottomBarState.value,
//        enter = slideInVertically(initialOffsetY = { it }),
//        exit = slideOutVertically(targetOffsetY = { it }),
    content = {
        BottomBarNavigation(navController = navController,mainScreenViewModel)
    })
}
@Composable
fun BottomBarNavigation(navController: NavController,mainScreenViewModel: MainScreenViewModel){
    BottomNavigation(
        backgroundColor = BottomBarColor,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        mainScreenViewModel.bottomNavItems.forEach{ item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(
                    text = item.title,
                    fontSize = 9.sp) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}