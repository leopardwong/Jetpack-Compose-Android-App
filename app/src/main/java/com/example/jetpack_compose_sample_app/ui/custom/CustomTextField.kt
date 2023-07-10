package com.example.jetpack_compose_sample_app.ui

import android.view.KeyEvent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomTextField(text: String,
                    modifier: Modifier = Modifier,
                    isPassword: Boolean = false,
                    textState: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue()) },
                    inputError:Boolean = false){
    val keyboardController = LocalSoftwareKeyboardController.current
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        placeholder = { Text(text = text, fontSize = 12.sp)},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        modifier = modifier
            .focusTarget()

            .onKeyEvent {
                when(it.nativeKeyEvent.keyCode){
                    KeyEvent.KEYCODE_ENTER -> {
                        focusManager.moveFocus(focusDirection = FocusDirection.Down,)
                        return@onKeyEvent true}
                    KeyEvent.KEYCODE_TAB -> {
                        focusManager.moveFocus(focusDirection = FocusDirection.Down,)
                        return@onKeyEvent true}
                    else ->{
                        false
                    }
                }
                false
            }
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),

        visualTransformation = if(!isPassword) VisualTransformation.None else {if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()},//if (passwordVisible.value && !isPassword) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            if(isPassword){
                val image = if (passwordVisible.value)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordVisible.value) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisible.value =! passwordVisible.value}){
                    Icon(imageVector  = image, description,tint = Color.Gray)
                }
            }
        },
        isError = inputError,
    )
}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomBasicTextField(text: String, modifier: Modifier = Modifier){
    var textState = remember { mutableStateOf(TextFieldValue()) }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column() {
        BasicTextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
            },
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth()
                ){
                    if (textState.value.text.isEmpty()) {
                        Text(
                            text = text,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.LightGray
                        )
                    }
                    innerTextField()
                }

            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            modifier = Modifier
                .onKeyEvent {
                    if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                        println(textState.value.text)
                    }
                    false
                }
                .fillMaxWidth(),
        )
        Divider(thickness = 1.dp, color = Color.LightGray)
    }
}


