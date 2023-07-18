package com.example.jetpack_compose_sample_app.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_compose_sample_app.Utils.updateLocale
import com.example.jetpack_compose_sample_app.ui.LoginScreen


class LoginScreenActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateLocale(this)

        setContent {
            LoginScreen(context = this)
        }
    }


}