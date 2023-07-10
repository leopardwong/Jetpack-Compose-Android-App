package com.example.jetpack_compose_sample_app.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jetpack_compose_sample_app.MainActivity
import com.example.jetpack_compose_sample_app.Utils.updateLocale
import com.example.jetpack_compose_sample_app.ui.custom.restartApp
import com.example.jetpack_compose_sample_app.ui.screen.MainScreenView

class MainScreenActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateLocale(this)
        setContent {
            MainScreenView(this)
        }
    }

}