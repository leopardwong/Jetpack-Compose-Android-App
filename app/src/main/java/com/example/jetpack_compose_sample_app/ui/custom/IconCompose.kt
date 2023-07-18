package com.example.jetpack_compose_sample_app.ui.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_sample_app.R

@Composable
fun BiometricIDIcon(){
    val checkedState = remember { mutableStateOf(true) }
    Switch(checked = checkedState.value,
        onCheckedChange = { checkedState.value = it },
        modifier = Modifier.padding(15.dp, 12.dp))
}
@Composable
fun ArrowIcon(){
    Image(
        painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
        contentDescription = "right arrow",
        contentScale = ContentScale.Fit,
        modifier = Modifier.padding(0.dp, 12.dp, 15.dp, 12.dp)
    )
}
@Composable
fun LanguageIcon(){
    Image(
        painter = painterResource(R.drawable.ic_language_select),
        contentDescription = "language select",
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(Color.LightGray),
        modifier = Modifier
            .size(40.dp)
            .padding(top = 10.dp)
    )
    Text(text = "English",
        fontSize = 17.sp,
        modifier = Modifier.padding(0.dp,12.dp),
        color = Color.LightGray )
    ArrowIcon()
}

@Composable
fun IconAndImage(imageId: Int, description: String, contentScale: ContentScale, modifier: Modifier) {
    Image(
        painter = painterResource(imageId),
        contentDescription = description,
        contentScale = contentScale,
        modifier = modifier,
    )
}
