package com.example.jetpack_compose_sample_app.ui.custom

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_sample_app.R
import com.example.jetpack_compose_sample_app.Utils
import com.example.jetpack_compose_sample_app.activity.MainScreenActivity
import com.example.jetpack_compose_sample_app.ui.datamodel.LanguageText


@Composable

fun TopBarVisible(title: String,
                  topBarState: MutableState<Boolean>,
                  context: Context){
    AnimatedVisibility(visible = topBarState.value,
        content = {
            TopBar(
                title = title,
                context = context)
        })
}

@Composable
fun TopBar(title: String,
           context:Context){
    val languageTextList = listOf(
        LanguageText("English","Change to English","en"),
        LanguageText("Traditional Chinese","Change to Traditional Chinese","zh"),
        LanguageText("Simplified Chinese","Change to Simplified Chinese","zh"),
    )
    var dropDownMenuExpanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = { Text(text = title, fontSize = 30.sp, fontWeight = Bold, modifier = Modifier) },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier,
        actions = {
            TopAppBarActionButton(imageVector = ImageVector.vectorResource(id = R.drawable.ic_language_select), description = "Options") {
                // show the drop down menu
                dropDownMenuExpanded = true
            }
            DropdownMenu(
                expanded = dropDownMenuExpanded,
                onDismissRequest = {
                    dropDownMenuExpanded = false
                },
                offset = DpOffset(x = 10.dp, y = (-60).dp)
            ) {
                // column scope
                // items are added vertically
                languageTextList.forEach {
                    DropdownMenuItem(onClick = {
                        Toast.makeText(context, it.showText, Toast.LENGTH_SHORT)
                            .show()
                        Utils.saveLocale(context, it.langCode)
                        val intent = Intent(context,MainScreenActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        context.startActivity(intent)
                        dropDownMenuExpanded = false
                    }) {
                        Text(it.title)
                    }
                }
            }
        }
    )
}

@Composable
fun TopAppBarActionButton(
    imageVector: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }, modifier = Modifier) {
        Icon(imageVector = imageVector, contentDescription = description)
    }
}

fun Activity.restartApp(){
    this.finishAffinity()

    this.baseContext?.packageName?.let {
        val intent = this.baseContext?.packageManager?.getLaunchIntentForPackage(it)
        val componentName = intent?.component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        this.startActivity(mainIntent)
    }
}
