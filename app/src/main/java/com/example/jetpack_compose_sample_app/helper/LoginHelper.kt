package com.example.jetpack_compose_sample_app.helper

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.jetpack_compose_sample_app.activity.MainScreenActivity

object LoginHelper {


    fun navigateToHome(result: Boolean,context:Context){
        if(result){
            val intent = Intent(context, MainScreenActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
            SharedPreferenceHelper(context).save("isLoggedIn",true)
        }else{
            Toast.makeText(context, "API call fails",
                Toast.LENGTH_SHORT)
                .show()
        }
    }

    //TODO firebase implementation
    fun validPassword(password: String):Boolean{
        return ( password == "1234")
    }

    fun validUserName(userName: String):Boolean{
        return (userName == "1234")
    }

}