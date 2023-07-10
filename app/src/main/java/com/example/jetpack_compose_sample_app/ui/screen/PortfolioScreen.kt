package com.example.jetpack_compose_sample_app.ui

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_sample_app.R
import com.example.jetpack_compose_sample_app.ui.datamodel.*
import com.example.jetpack_compose_sample_app.ui.theme.LightGray
import com.example.jetpack_compose_sample_app.ui.viewmodel.PortfolioScreenViewModel

@Composable
fun PortfolioScreen(context:Context) {
    val portfolioScreenViewModel = PortfolioScreenViewModel(context)
    val reviewListContent = portfolioScreenViewModel.reviewListContent
    val manageListContent = portfolioScreenViewModel.manageListContent
    LazyColumn(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .height(LocalConfiguration.current.screenHeightDp.dp)
        .background(Color.LightGray.copy(0.2f))){
        item {
            ManageSessionUI(manageListContent)
        }
        item {
            Text(text =reviewListContent.title ,
                fontSize = 17.sp,
                color =  Color.DarkGray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(15.dp, 22.dp, 0.dp, 15.dp) )
        }
        items(reviewListContent.reviewItem.size){ index ->
            val reviewContent = reviewListContent.reviewItem
            Row(
                modifier = Modifier
                    .border(BorderStroke(1.dp, LightGray))
                    .background(Color.White)
                    .height(60.dp)
                    .fillMaxWidth()
                    .clickable (onClick = {
                        //when(reviewContent[index].title){}
                        reviewContent[index].action
                        println("====${reviewContent[index].title}")
                    }),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(
                    id = reviewContent[index].icon),
                    contentDescription = "Portfolio icon",
                    modifier = Modifier.padding(horizontal = 10.dp))
                Text(
                    text = reviewContent[index].title,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(3f),
                    color =  Color.DarkGray)
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                    contentDescription = "right arrow",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.padding(15.dp, 12.dp, 15.dp, 12.dp)
                )
            }
        }
    }
}

@Composable
fun ManageSessionUI(manageListContent: ManageListContent){
    Column(modifier = Modifier) {
        Text(text = manageListContent.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(15.dp, 22.dp, 0.dp, 15.dp))
        PortfolioLazyVerticalGrid(manageListContent)
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PortfolioLazyVerticalGrid(manageListContent: ManageListContent){
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        Modifier.height(120.dp)
    ) {
        items(manageListContent.manageItem.size) {
            val manageItems = manageListContent.manageItem
            Box(
                modifier = Modifier
                    .height(110.dp)
                    .fillMaxWidth()
                    .background(Color.White)
                    .border(0.5.dp, Color.LightGray.copy(0.5f))
                    .clickable {

                    }
                    .padding(top = 20.dp)
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.TopCenter,


                ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(manageItems[it].icon),
                        contentDescription = manageItems[it].title
                    )
                    Text(
                        text = manageItems[it].title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}