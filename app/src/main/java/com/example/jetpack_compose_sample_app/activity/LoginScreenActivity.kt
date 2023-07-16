package com.example.jetpack_compose_sample_app.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_compose_sample_app.Utils.updateLocale
import com.example.jetpack_compose_sample_app.helper.SharedPreferenceHelper
import com.example.jetpack_compose_sample_app.ui.LoginPageBody


class LoginScreenActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateLocale(this)

        setContent {
            LoginPageBody(context = this)
        }
    }


}