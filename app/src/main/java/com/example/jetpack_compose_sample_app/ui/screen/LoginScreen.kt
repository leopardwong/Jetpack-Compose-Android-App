package com.example.jetpack_compose_sample_app.ui

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_sample_app.R
import com.example.jetpack_compose_sample_app.Utils.saveLocale
import com.example.jetpack_compose_sample_app.activity.RegisterScreenActivity
import com.example.jetpack_compose_sample_app.helper.LoginHelper.login
import com.example.jetpack_compose_sample_app.ui.datamodel.LoginPageText
import com.example.jetpack_compose_sample_app.ui.datamodel.Password
import com.example.jetpack_compose_sample_app.ui.datamodel.UserName
import com.example.jetpack_compose_sample_app.ui.viewmodel.LoginScreenViewModel


val contextSize = 12.sp

@Composable
fun LoginScreen(context:Context) {
    val loginScreenViewModel = LoginScreenViewModel(context)
    val userNameState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
//    var vailUserNameInput by remember { mutableStateOf(false) }
//    var vailPasswordInput by remember { mutableStateOf(false) }
    var dropDownMenuExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 40.dp)
    ) {
        Row {
            Spacer(Modifier.weight(1f))
            Box(modifier = Modifier){
                CustomOutlinedButton(imageId = R.drawable.ic_language_select, onClick = {
                    dropDownMenuExpanded = true
                })
                DropdownMenu(
                    expanded = dropDownMenuExpanded,
                    onDismissRequest = {
                        dropDownMenuExpanded = false
                    },
                    // play around with these values
                    // to position the menu properly
                    offset = DpOffset(x = 10.dp, y = (-60).dp)
                ) {
                    loginScreenViewModel.languageTextList.forEach {
                        DropdownMenuItem(onClick = {
                            Toast.makeText(context, it.showText, Toast.LENGTH_SHORT)
                                .show()
                            saveLocale(context,it.langCode)
                            dropDownMenuExpanded = false
                        }) {
                            Text(it.title)
                        }
                    }
                }
            }

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp, 0.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            MsgColumn(context,loginScreenViewModel.loginMessage)
            InputColumn(userName = loginScreenViewModel.userTextField,
                password = loginScreenViewModel.passwordTextField,
                userNameState = userNameState,
                passwordState = passwordState,
//                userInputError = vailUserNameInput,
//                passwordInputError = vailPasswordInput
            )

            LoginButtonColumn(
                context = context,
                userName = userNameState.value.text,
                password = passwordState.value.text,
                loginScreenViewModel = loginScreenViewModel,
            )

            Line()

            RegisterButtonColumn(context)

            Line()

            OtherText(loginScreenViewModel)
        }
    }
}
@Composable
fun MsgColumn(context: Context,msg: LoginPageText){
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = context.getString(R.string.login),
            fontSize = 30.sp,
            fontWeight =  FontWeight.Bold)
        Text(text = msg.body,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold)
        Text(text = msg.body2,
            fontSize = contextSize)
    }
}

@Composable
fun InputColumn(userName: UserName,
                password: Password,
                userNameState: MutableState<TextFieldValue>,
                passwordState: MutableState<TextFieldValue>,
//                userInputError:Boolean = false,
//                passwordInputError:Boolean = false
){
    Column(verticalArrangement = Arrangement.spacedBy(space = 20.dp)) {
        //input field
        Text(text = "Email address",
            fontSize = contextSize)
        CustomTextField("Email address",
            isPassword = false,
            textState = userNameState,
//            inputError = userInputError
        )
        
        Text(text = password.title,
            fontSize = contextSize)
        CustomTextField(password.textField,
            isPassword = true,
            textState = passwordState,
//            inputError = passwordInputError
        )
    }
}

@Composable
fun LoginButtonColumn(
    context: Context,
    userName: String,
    password: String,
    loginScreenViewModel: LoginScreenViewModel
){
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        LoginButton(
            text = context.getString(R.string.login),
            color = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
            borderColor = Color.Transparent,
            onClickEvent = {
                //TODO change logic: should call api after will login success
                loginScreenViewModel.loadAPI{
                    login(context,userName,password,it)
                }
            })

        CustomTextButton(context.getString(R.string.biometric_sign_in),
            ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Transparent),
                borderColor = Color.Red,
                onClickEvent = {
                    Toast.makeText(context, "Biometric not yet ready to use",
                        Toast.LENGTH_SHORT)
                        .show()
                })
        
        CustomClickableText(context.getString(R.string.forget_password),contextSize,onClick = {})
        CustomClickableText(context.getString(R.string.forget_username),contextSize, onClick = {})

    }
}

@Composable
fun RegisterButtonColumn(context: Context){
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        CustomTextButton(context.getString(R.string.register_account),
            ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Transparent),
                borderColor = Color.Red,
                onClickEvent = {
                    val intent = Intent(context, RegisterScreenActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                })
        CustomTextButton(context.getString(R.string.register_account_with),
            ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Transparent),
                borderColor = Color.Red,
                onClickEvent = {
                    Toast.makeText(context, "social sign in not yet ready to use",
                        Toast.LENGTH_SHORT)
                        .show()
                })

    }
}
@Composable
fun OtherText(loginScreenViewModel: LoginScreenViewModel){
    val ctx = LocalContext.current
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        loginScreenViewModel.otherText.forEach {
            CustomClickableText(text = it.title, size = contextSize, onClick = {showWebView(ctx,it.url)})
        }
        Text(text = loginScreenViewModel.version,
            fontSize = contextSize)
    }
}


@Composable
fun Line(){
    Row(modifier = Modifier
        .fillMaxWidth()
        //.padding(0.dp, 15.dp)
    ) {
        Divider(thickness = 1.dp, color = Color.LightGray)
    }
}



