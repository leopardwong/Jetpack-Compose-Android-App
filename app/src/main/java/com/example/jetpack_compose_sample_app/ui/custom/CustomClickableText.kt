package com.example.jetpack_compose_sample_app.ui


import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit

@Composable
fun CustomClickableText(text:String,size: TextUnit,onClick: ()->Unit){
    Text(
        text = text,
        fontSize = size,
        modifier = Modifier.clickable(
            onClick = onClick
        ),
        style = TextStyle(textDecoration = TextDecoration.Underline)
    )
}