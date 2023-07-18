package com.example.jetpack_compose_sample_app.ui.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_sample_app.R
import com.example.jetpack_compose_sample_app.activity.LoginScreenActivity
import com.example.jetpack_compose_sample_app.helper.LoginHelper.signUp
import com.example.jetpack_compose_sample_app.ui.CustomTextButton
import com.example.jetpack_compose_sample_app.ui.CustomTextField
import com.example.jetpack_compose_sample_app.ui.LoginButton
import com.example.jetpack_compose_sample_app.ui.contextSize

@Composable
fun RegisterScreen(context: Context){
    val userEmailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }// TODO not in use
    val userNameState = remember { mutableStateOf(TextFieldValue()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp, 0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = context.getString(R.string.register_account),
            fontSize = 30.sp,
            fontWeight =  FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        InputColumn(
            context = context,
            userEmailState = userEmailState,
            userNameState = userNameState, // TODO not in use
            passwordState = passwordState,
//                userInputError = vailUserNameInput,
//                passwordInputError = vailPasswordInput
        )
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(
            text = context.getString(R.string.register_account),
            color = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
            borderColor = Color.Transparent,
            onClickEvent = {
                signUp(context,userEmailState.value.text,passwordState.value.text)
            })
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextButton("Back to Login Screen",
            ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Transparent),
                borderColor = Color.Red,
                onClickEvent = {
                val intent = Intent(context, LoginScreenActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            })
    }



}

@Composable
fun InputColumn(context: Context,
                userEmailState: MutableState<TextFieldValue>,
                userNameState: MutableState<TextFieldValue>,// TODO not in use
                passwordState: MutableState<TextFieldValue>,
){
    Column(verticalArrangement = Arrangement.spacedBy(space = 20.dp)) {
        //input field
        Text(text = context.getString(R.string.username),
            fontSize = contextSize
        )
        CustomTextField(context.getString(R.string.username_input),
            isPassword = false,
            textState = userNameState,
        )
        Text(text = "Email address",
            fontSize = contextSize
        )
        CustomTextField("Email address",
            isPassword = false,
            textState = userEmailState,
        )

        Text(text = context.getString(R.string.password),
            fontSize = contextSize
        )
        CustomTextField(context.getString(R.string.password_input_hit),
            isPassword = true,
            textState = passwordState,
        )
    }
}