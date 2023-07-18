package com.example.jetpack_compose_sample_app.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.jetpack_compose_sample_app.activity.LoginScreenActivity
import com.example.jetpack_compose_sample_app.activity.MainScreenActivity

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object LoginHelper {
    val auth = Firebase.auth

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


    fun signUp(context: Context, email: String, password: String ){
        // Sign Up logic
        val activity = context as Activity
        if(email.isNotEmpty() && password.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(
                            activity,
                            "Register Success",
                            Toast.LENGTH_SHORT,
                        ).show()
                        val intent = Intent(context, LoginScreenActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        context.startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            activity,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        } else{
            Toast.makeText(
                activity,
                "Null",
                Toast.LENGTH_SHORT,
            ).show()
        }

    }

    fun login(context: Context, email: String, password: String,result: Boolean){
        val activity = context as Activity
        if(result){
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(context, MainScreenActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        context.startActivity(intent)
                        SharedPreferenceHelper(context).save("isLoggedIn",true)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            activity,
                            "Login failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }
        }else{
            Toast.makeText(context, "API call fails",
                Toast.LENGTH_SHORT)
                .show()
        }

    }

}