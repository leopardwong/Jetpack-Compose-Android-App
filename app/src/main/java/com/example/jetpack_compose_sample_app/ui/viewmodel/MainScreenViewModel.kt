package com.example.jetpack_compose_sample_app.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_sample_app.R
import com.example.jetpack_compose_sample_app.ui.datamodel.BottomNavItem

class MainScreenViewModel(context: Context):ViewModel(){
    val bottomNavItems = listOf(
        BottomNavItem(context.getString(R.string.home), R.drawable.ic_home,"home"),
        BottomNavItem(context.getString(R.string.portfolio),R.drawable.ic_profile,"portfolio"),
        BottomNavItem(context.getString(R.string.for_you),R.drawable.ic_for_you,"for_you"),
        BottomNavItem(context.getString(R.string.setting),R.drawable.ic_setting,"setting")
    )

}