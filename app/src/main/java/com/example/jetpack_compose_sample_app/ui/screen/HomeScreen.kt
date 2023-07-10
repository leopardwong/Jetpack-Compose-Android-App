package com.example.jetpack_compose_sample_app.ui.screen

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jetpack_compose_sample_app.R
import com.example.jetpack_compose_sample_app.ui.Line
import com.example.jetpack_compose_sample_app.ui.custom.CustomBoldText
import com.example.jetpack_compose_sample_app.ui.custom.DoughnutChart1
import com.example.jetpack_compose_sample_app.ui.custom.IconAndImage
import com.example.jetpack_compose_sample_app.ui.datamodel.*
import com.example.jetpack_compose_sample_app.ui.theme.BannerColor
import com.example.jetpack_compose_sample_app.ui.theme.GreenTextColor
import com.example.jetpack_compose_sample_app.ui.viewmodel.HomeScreenViewModel
import java.util.*

var verticalSpacedArrangement = Arrangement.spacedBy(24.dp)

@Composable
fun HomeScreen(context: Context){
    val homeScreenViewModel = HomeScreenViewModel(context)
    Column(
        modifier = Modifier
            .background(Color.LightGray.copy(alpha = 0.1f))
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ProfileText(context,homeScreenViewModel)

        Column(modifier = Modifier
            .padding(horizontal = 15.dp)
            .padding(top = 20.dp),
            verticalArrangement = verticalSpacedArrangement
        ) {

            ImagePortfolio()

            QuickLinksSession(homeScreenViewModel.quickLinksContent)

            MPFGraph(context,homeScreenViewModel)

            CanvasChartTemple(context,homeScreenViewModel)

            //CanvasChartTemple(homeScreenViewModel.templeWithChart2)

            DiscoverMoreDescriptionText(context,homeScreenViewModel)

            HomeContext(homeScreenViewModel.sampleTextList)

            LearnMoreShortCut(homeScreenViewModel.learnMoreLists)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileText(context: Context,homeScreenViewModel: HomeScreenViewModel){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(BannerColor)
        .padding(vertical = 20.dp, horizontal = 15.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = CenterHorizontally) {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp), modifier = Modifier) {
                CustomBoldText(text = "${context.getString(R.string.hi)} **${homeScreenViewModel.profileContent.name}**,",
                    size = 20.sp,
                    fontWeight = null,
                    color = Color.White)
                Text(text = homeScreenViewModel.profileContent.text,
                    fontSize = 17.sp,
                    color = Color.White)
                Text(text = "${context.getString(R.string.phone)} ${homeScreenViewModel.profileContent.phone}",
                    fontSize = 18.sp,
                    fontWeight = Normal,
                    color = Color.White)
                Text(text = "${context.getString(R.string.email)} ${homeScreenViewModel.profileContent.email}",
                    fontSize = 18.sp,
                    fontWeight = Normal,
                    color = Color.White)
            }
//            LazyVerticalGrid( cells = GridCells.Fixed(2),Modifier.height(100.dp)){
//                items(homeScreenViewModel.profileContent.profileCount.size) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(Color.Transparent)
//                            .padding(top = 8.dp)
//
//                        ){
//                        Row(modifier = Modifier,
//                            horizontalArrangement = Arrangement.spacedBy(4.dp),
//                        verticalAlignment = Alignment.Bottom) {
//                            Text(text = homeScreenViewModel.profileContent.profileCount[it].num.toString(),
//                                color = Color.White,
//                                fontSize = 30.sp,
//                                fontWeight = Bold,)
//                            Text(text = homeScreenViewModel.profileContent.profileCount[it].title,
//                                color = Color.White,
//                                fontSize = 14.sp,
//                                fontWeight = Bold)
//                        }
//
//                    }
//                }
//            }
        }

    }
}

@Composable
fun ImagePortfolio(){
    Column() {
        Box(modifier = Modifier
            .fillMaxWidth()
            .size(128.dp)
            .background(Color.LightGray)){
            Row (modifier = Modifier.align(BottomCenter)){
                IconAndImage(imageId = R.drawable.iphone,
                    description = "",
                    contentScale = ContentScale.Fit ,
                    modifier =Modifier.size(150.dp) )
                Text(
                    text = "Stay tuned for more features",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(30.dp)

                 )
            }

        }
    }
}

// Quick Links Session
@Composable
fun QuickLinksSession(quickLinksContent:QuickLinksContent){
    Column(modifier = Modifier
    ){
        Text(text = quickLinksContent.title,
            fontSize = 16.sp,
            fontWeight = Light,
            modifier = Modifier.padding(bottom = 24.dp))
        LazyVerticalGrid(quickLinksContent.quickLinksList)
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyVerticalGrid(quickLinks:List<QuickLinks>){
    LazyVerticalGrid( cells = GridCells.Fixed(3),Modifier.height(220.dp)){
        items(quickLinks.size) {
            Box(
                modifier = Modifier
                    .height(110.dp)
                    .fillMaxWidth()
                    .background(Color.White)
                    .border(0.5.dp, Color.LightGray.copy(0.5f))
                    .clickable(
                        onClick = quickLinks[it].action
                    )
                    .padding(top = 20.dp)
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.TopCenter,

            ){
                Column(modifier = Modifier,
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(imageVector = ImageVector.vectorResource(quickLinks[it].icon), contentDescription = quickLinks[it].title)
                    Text(text = quickLinks[it].title,
                        fontSize = 13.sp,
                        fontWeight = Normal,
                        textAlign = TextAlign.Center)
                }
            }
        }
    }
}

//Canvas Chart Session
@Composable
fun MPFGraph(context:Context,homeScreenViewModel: HomeScreenViewModel){
    Column(verticalArrangement = verticalSpacedArrangement) {
        Text(text = context.getString(R.string.your_policy_account),
            fontSize = 16.sp,
            fontWeight = Light,
            modifier = Modifier.padding(bottom = 8.dp))
        Column(modifier = Modifier
            .background(Color.White)
            .border(0.5.dp, Color.LightGray)
            .padding(horizontal = 20.dp)
            .padding(top = 25.dp)) {
            Row() {
                Text(text = homeScreenViewModel.mpfChart.title,
                    fontSize = 14.sp,
                    fontWeight = Bold,
                    modifier = Modifier)
                Spacer(modifier = Modifier.weight(1f))
                Icon(painter = painterResource(
                    id = R.drawable.ic_arrow_right_outline),
                    contentDescription = "",
                    tint = Color.Red)
            }
            Column() {
                Text(
                    text = homeScreenViewModel.mpfChart.context,
                    fontSize = 20.sp,
                    fontWeight = Bold,
                    modifier = Modifier)
                Text(
                    text = "${context.getString(R.string.account_id)} ${homeScreenViewModel.mpfChart.id}",
                    fontSize = 16.sp,
                    fontWeight = Light,
                    modifier = Modifier)
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp)
                    .wrapContentSize(Center)){
                    //Total balance
                    Column(modifier = Modifier.align(Center), horizontalAlignment = CenterHorizontally) {
                        Text(
                            text = homeScreenViewModel.mpfChart.totalAmount,
                            fontSize = 20.sp,
                            fontWeight = Bold)
                        Text(
                            text = context.getString(R.string.total_balance),
                            fontWeight = Light)
                    }

                    DoughnutChart1()

                }
                Text(text = "${context.getString(R.string.balance_num)} ${homeScreenViewModel.mpfChart.time}",
                    fontSize = 12.sp,
                    fontWeight = Normal,
                    modifier = Modifier.padding(bottom = 20.dp))
                Line()
                Column(modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = context.getString(R.string.net_contribution),
                            fontSize = 14.sp,
                            fontWeight = Normal,
                            modifier = Modifier.weight(2f))
                        Text(text = "HKD ${homeScreenViewModel.mpfChart.gainOrLose}",
                            fontSize = 16.sp,
                            fontWeight = Normal,
                            modifier = Modifier.weight(1f))
                    }
                    Row() {
                        Text(text = context.getString(R.string.account_gain),
                            fontSize = 14.sp,
                            fontWeight = Normal,
                            modifier = Modifier.weight(2f))
                        Text(text = "HKD ${homeScreenViewModel.mpfChart.gainOrLose}",
                            fontSize = 16.sp,
                            fontWeight = Normal,
                            modifier = Modifier.weight(1f),
                            color = GreenTextColor )
                    }
                }
                Line()
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(IntrinsicSize.Min),
                    shape = RectangleShape,
                    border = BorderStroke(2.dp, Color.Transparent),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)

                ) {
                    Image(painter = painterResource(id = R.drawable.ic_arrow_down), contentDescription = "")
                    Text(text = context.getString(R.string.view_more),
                        fontSize = 14.sp ,
                        fontWeight = SemiBold,
                        modifier = Modifier.padding(start = 4.dp))

                }
            }
        }
        NoteText(context)
    }
}

@Composable
fun CanvasChartTemple(context:Context,homeScreenViewModel: HomeScreenViewModel){
    Column(modifier = Modifier, verticalArrangement = verticalSpacedArrangement){
        Column(modifier = Modifier
            .background(Color.White)
            .border(0.5.dp, Color.LightGray)
            .padding(horizontal = 20.dp)
            .padding(top = 25.dp)) {
            Row() {
                Text(text = homeScreenViewModel.templeWithChart1.title,
                    fontSize = 14.sp,
                    fontWeight = Bold,
                    modifier = Modifier)
                Spacer(modifier = Modifier.weight(1f))
                Icon(painter = painterResource(id = R.drawable.ic_arrow_right_outline), contentDescription = "", tint = Color.Red)
            }
            Column() {
                Text(text = homeScreenViewModel.templeWithChart1.context,
                    fontSize = 20.sp,
                    fontWeight = Bold,
                    modifier = Modifier)
                Text(text = "${context.getString(R.string.policy_no)} ${homeScreenViewModel.templeWithChart1.id}",
                    fontSize = 16.sp,
                    fontWeight = Light,
                    modifier = Modifier)
                Text(text = "${context.getString(R.string.life_insured)} ${homeScreenViewModel.templeWithChart1.name}",
                    fontSize = 16.sp,
                    fontWeight = Light,
                    modifier = Modifier)
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp)
                    .wrapContentSize(Alignment.Center)){
                    Column(modifier = Modifier.align(Center), horizontalAlignment = CenterHorizontally) {
                        Text(text = homeScreenViewModel.templeWithChart1.totalAmount,
                            fontSize = 20.sp,
                            fontWeight = Bold)
                        Text(text = context.getString(R.string.total_balance), fontWeight = Light)
                    }
                    DoughnutChart1()
                }
                Text(text = "As at ${homeScreenViewModel.templeWithChart1.time}",
                    fontSize = 12.sp,
                    fontWeight = Normal,
                    modifier = Modifier.padding(bottom = 20.dp))
                Line()
                Row() {
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(IntrinsicSize.Min),
                        shape = RectangleShape,
                        border = BorderStroke(2.dp, Color.Transparent),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)

                    ) {
                        Image(painter = painterResource(id = R.drawable.ic_arrow_down), contentDescription = "")
                        Text(text = "View more",
                            fontSize = 14.sp ,
                            fontWeight = SemiBold,
                            modifier = Modifier.padding(start = 4.dp))

                    }
                }
            }
        }
        NoteText(context)
    }
}

@Composable
fun NoteText(context:Context){
    Column() {
        Text(text = context.getString(R.string.notes),
            fontSize = 14.sp,
            fontWeight = SemiBold)
        CustomBoldText(
            text = context.getString(R.string.note_text),
            size = 14.sp,
            fontWeight = Normal)
        ClickableText(text = AnnotatedString(context.getString(R.string.view_all)),
            onClick = {},
            style = TextStyle(
            textDecoration = TextDecoration.combine(
                listOf(
                    TextDecoration.Underline,
                )
            ), fontWeight = Bold
        ))
    }
}


// Discover More Session
@Composable
fun DiscoverMoreDescriptionText(context:Context,homeScreenViewModel: HomeScreenViewModel){
    Column(verticalArrangement = verticalSpacedArrangement,) {
        Text(text = context.getString(R.string.you_may_like),
            fontSize = 16.sp,
            fontWeight = Light)
        CustomBoldText(text = context.getString(R.string.discover_more), size = 30.sp, fontWeight = null)
        Text(text = homeScreenViewModel.sampleText.toString(),
            fontSize = 14.sp,
            fontWeight = SemiBold)
    }
    ImageText(context,homeScreenViewModel)
}

@Composable
fun ImageText(context:Context,homeScreenViewModel: HomeScreenViewModel){
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .background(Color.White)
            .padding(bottom = 24.dp),
    )
    {
        AsyncImage(
            model = "https://fastly.picsum.photos/id/13/2500/1667.jpg?hmac=SoX9UoHhN8HyklRA4A3vcCWJMVtiBXUg0W4ljWTor7s",
            contentDescription = null)
        Box(modifier = Modifier.padding(horizontal = 14.dp), ) {
            Column(modifier = Modifier.padding(horizontal = 14.dp)) {
                Text(text = context.getString(R.string.sample_text_title),
                    fontSize = 15.sp,
                    fontWeight = Bold)
                Text(text = homeScreenViewModel.sampleText.toString(),
                    fontSize = 15.sp,
                    fontWeight = Normal)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .size(42.dp)
                    .background(Color.White)
                    .padding(vertical = 4.dp)
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.CenterStart
            ){
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxWidth()
                        .width(IntrinsicSize.Min),
                    shape = RectangleShape,
                    border = BorderStroke(2.dp, Color.Transparent),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)

                        ) {
                    Image(painter = painterResource(id = R.drawable.ic_arrow_right_with_outline), contentDescription = "")
                    Text(text = "Plan Now",
                        fontSize = 14.sp ,
                        fontWeight = SemiBold,
                        modifier = Modifier.padding(start = 4.dp))
                    Spacer(modifier = Modifier.weight(1f))

                }
            }
        }

    }
}

// Home Context Session
@Composable
fun HomeContext(sampleTextList: List<String>){
    val textSize = 12.sp
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        AsyncImage(
            model = "https://fastly.picsum.photos/id/49/1280/792.jpg?hmac=NnUJy0O9-pXHLmY2loqVs2pJmgw9xzuixgYOk4ALCXU",
            contentDescription = null)
        Text(text = sampleTextList[0], fontSize = 16.sp, fontWeight = Normal)
        Text(text = sampleTextList[1], fontSize = textSize, fontWeight = Bold)
        Text(text = sampleTextList[2], fontSize = textSize, fontWeight = Normal)
        Text(text = sampleTextList[3], fontSize = textSize, fontWeight = Normal)
        Text(text = sampleTextList[4], fontSize = textSize, fontWeight = Normal)
    }
}

//Learn More Session
@Composable
fun LearnMoreShortCutItem(learnMoreList: String){
    Row(){
        Icon(painter = painterResource(id = R.drawable.ic_arrow_right_without_outline), contentDescription = "", tint = Color.Red)
        Text(text = learnMoreList,
            fontSize = 14.sp ,
            fontWeight = SemiBold,
            modifier = Modifier
            .padding(start = 4.dp)
            .clickable { })
        Spacer(modifier = Modifier.weight(1f))
    }
}
@Composable
fun LearnMoreShortCut(learnMoreLists:List<String>){
    LazyColumn(modifier = Modifier.height(200.dp),verticalArrangement = Arrangement.spacedBy(16.dp)){
        items(5) { index ->
            LearnMoreShortCutItem(learnMoreLists[index])
        }
    }
}
