package com.darkstoreapp.test.features.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Colors {
    val Blue500 = Color(0xFF0053CC)
    val Blue400 = Color(0xFF0077C8)
    val Gray900 = Color(0xFF212529)
    val Red400 = Color(0xFFEB5757)
    val Green500 = Color(0xFF36A004)
}

private val AppColors = lightColors(
    primary = Colors.Blue500,
    primaryVariant = Colors.Blue500,
    secondary = Colors.Blue400,
    secondaryVariant = Colors.Blue400,
    background = Color.White,
    surface = Color.White,
    error = Colors.Red400,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Colors.Gray900,
    onSurface = Colors.Gray900,
    onError = Color.White,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = AppColors,
        content = content
    )
}