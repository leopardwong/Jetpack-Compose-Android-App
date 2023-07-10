package com.example.jetpack_compose_sample_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.jetpack_compose_sample_app.activity.LoginScreenActivity
import com.example.jetpack_compose_sample_app.activity.MainScreenActivity
import com.example.jetpack_compose_sample_app.helper.SharedPreferenceHelper


class MainActivity : ComponentActivity() {
    companion object {
        lateinit  var appContext: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContext = applicationContext


        val isLoggedIn = SharedPreferenceHelper(this).getValueBoolean("isLoggedIn",false)

        if(isLoggedIn){
            val intent = Intent(appContext, MainScreenActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            appContext.startActivity(intent)
            finish()
        }else{
            val intent = Intent(appContext, LoginScreenActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            appContext.startActivity(intent)
            finish()
        }
    }

}