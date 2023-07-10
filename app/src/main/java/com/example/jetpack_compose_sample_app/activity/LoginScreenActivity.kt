package com.example.jetpack_compose_sample_app.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_compose_sample_app.MainActivity
import com.example.jetpack_compose_sample_app.Utils
import com.example.jetpack_compose_sample_app.Utils.updateLocale
import com.example.jetpack_compose_sample_app.helper.SharedPreferenceHelper
import com.example.jetpack_compose_sample_app.ui.LoginPageBody


class LoginScreenActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateLocale(this)


        setContent {
            LoginPageBody(
                context = this,
                loginState = { result ->
                    if(result){
                        val intent = Intent(applicationContext, MainScreenActivity::class.java)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        applicationContext.startActivity(intent)
                        SharedPreferenceHelper(this).save("isLoggedIn",true)
                        finish()
                    }else{
                        Toast.makeText(applicationContext, "Call api False",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            )
        }
    }


}