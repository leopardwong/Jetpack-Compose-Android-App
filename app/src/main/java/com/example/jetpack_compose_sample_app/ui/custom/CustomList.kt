package com.example.jetpack_compose_sample_app.ui.custom

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_sample_app.R
import com.example.jetpack_compose_sample_app.ui.datamodel.ForYouListContent
import com.example.jetpack_compose_sample_app.ui.theme.LightGray

@Composable
fun CustomSettingList(title: String, subtitles: List<String>, icon: Unit?){
    LazyColumn() {
        item {
            Row(
                modifier = Modifier
                    .background(LightGray)
                    .height(60.dp)
                    .fillMaxWidth(),
            ) {
                Text(text =title ,
                    fontSize = 17.sp,
                    color =  Color.DarkGray,
                    modifier = Modifier.padding(15.dp, 22.dp, 0.dp, 15.dp) )
            }
        }
        items(subtitles){ subtitle ->
            Row(
                modifier = Modifier
                    .border(BorderStroke(1.dp, LightGray))
                    .background(Color.White)
                    .height(50.dp)
                    .fillMaxWidth()
                    .clickable {
                        //TODO
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = subtitle,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(15.dp, 12.dp),
                    color =  Color.DarkGray)
                Spacer(modifier = Modifier.weight(1f))
                when(subtitle){
                    "Language" -> LanguageIcon()
                    "Biometric ID" -> BiometricIDIcon()
                    else -> ArrowIcon()
                }
            }
        }
    }
}

@Composable
fun CustomForYouList (forYouListContents: List<ForYouListContent>) {
    LazyColumn(
        modifier = Modifier.padding(20.dp, 25.dp, 20.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(forYouListContents) { forYouListContent ->
            Row(
                modifier = Modifier
                    .background(Color.White)
                    .border(BorderStroke(1.dp, Color.LightGray))
                    .height(100.dp)
                    .fillMaxWidth()
                    .clickable(onClick = {forYouListContent.action}),
            ){
                Column(modifier = Modifier.padding(15.dp, 20.dp, 0.dp, 0.dp)){
                    IconAndImage(imageId = forYouListContent.icon, description = "forYouIcon", contentScale = ContentScale.Fit, modifier = Modifier)
                    Text(text = forYouListContent.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black)
                    Text(text = forYouListContent.description,
                        fontSize = 14.sp,
                        color = Color.Black)
                }
                Spacer(modifier = Modifier.weight(1f))
                IconAndImage(forYouListContent.image, "forYouImage", ContentScale.Fit, Modifier.size(150.dp))
            }
        }
    }

}

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

