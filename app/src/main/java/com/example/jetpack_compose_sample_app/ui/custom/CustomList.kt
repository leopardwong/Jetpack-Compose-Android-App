package com.example.jetpack_compose_sample_app.ui.custom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

