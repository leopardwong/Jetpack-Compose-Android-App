package com.example.jetpack_compose_sample_app.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextButton(text:String, color: ButtonColors, borderColor: Color, onClickEvent: () -> Unit){
    Button(
        onClick = onClickEvent,
        colors = color,
        modifier = Modifier.fillMaxWidth(),//.padding(0.dp,30.dp),
        shape = RectangleShape,
        border = BorderStroke(2.dp, borderColor)
    ) {
        Text(text = text,
            modifier = Modifier.padding(0.dp,10.dp),
            color = Color.Black)
    }
}

@Composable
fun LoginButton(text:String, color: ButtonColors, borderColor: Color, onClickEvent: () -> Unit){
    Button(
        onClick = onClickEvent,
        colors = color,
        modifier = Modifier.fillMaxWidth(),//.padding(0.dp,30.dp),
        shape = RectangleShape,
        border = BorderStroke(2.dp, borderColor)
    ) {
        Text(text = text,
            modifier = Modifier.padding(0.dp,10.dp),
            color = Color.Black)
    }
}

@Composable
fun CustomImageButton(imageId: Int){
    Button(onClick = { /*TODO*/ },
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Transparent),
       // border = BorderStroke(4.dp, Color.Transparent)
    ) {
        val modifier = Modifier
            .size(40.dp)
            .align(alignment = Alignment.Top)
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Contact profile picture",
            contentScale = ContentScale.Fit,
            modifier = modifier

        )
    }
}

@Composable
fun CustomOutlinedButton(onClick: ()->Unit,imageId: Int){
    OutlinedButton(onClick = onClick,
        modifier = Modifier,
        shape = RectangleShape,
        border = BorderStroke(2.dp, Color.Transparent),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
        val modifier = Modifier
            .size(40.dp)
            .align(alignment = Alignment.Top)
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Contact profile picture",
            contentScale = ContentScale.Fit,
            modifier = modifier
        )
    }
}
