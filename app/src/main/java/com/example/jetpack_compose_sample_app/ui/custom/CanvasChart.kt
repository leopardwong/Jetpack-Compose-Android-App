package com.example.jetpack_compose_sample_app.ui.custom

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class MPFData(val title:String, val color: Color, val num:Float)
val MPFDataList = listOf(
    MPFData("Mango", Color(0xFFFF6384),65f),
    MPFData("Banana", Color(0xFFFFCE56),40f),
    MPFData("Apple", Color(0xFF36A2EB),25f),
    MPFData("Melon", Color(0xFF448AFF),20f),
)
@Composable
fun DoughnutChart1(
    values: List<Float> = listOf(65f, 40f, 25f, 20f),
    colors: List<Color> = listOf(
        Color(0xFFFF6384),
        Color(0xFFFFCE56),
        Color(0xFF36A2EB),
        Color(0xFF448AFF)
    ),
    legend: List<String> = listOf("Mango", "Banana", "Apple", "Melon"),
    size: Dp = 180.dp,
    thickness: Dp = 36.dp
) {
    // Sum of all the values
    val sumOfValues = values.sum()

    // Calculate each proportion
    val proportions = values.map {
        it * 100 / sumOfValues
    }
    // Convert each proportion to angle
    val sweepAngles = proportions.map {
        360 * it / 100
    }
    Canvas(
        modifier = Modifier
            .size(size = size)
//            .border(1.dp, Color.Green)
    ) {

        var startAngle = -90f

        for (i in values.indices) {
            drawArc(
                color = colors[i],
                startAngle = startAngle,
                sweepAngle = sweepAngles[i],
                useCenter = false,
                style = Stroke(width = thickness.toPx(), cap = StrokeCap.Butt)
            )
            startAngle += sweepAngles[i]
        }

    }

}