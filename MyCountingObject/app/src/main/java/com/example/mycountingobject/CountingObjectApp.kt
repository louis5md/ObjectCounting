package com.example.mycountingobject

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycountingobject.ui.screen.CountingScreen
import com.example.mycountingobject.ui.screen.OverviewScreen
import com.example.mycountingobject.ui.theme.MyCountingObjectTheme

@Composable
fun CountingObjectApp(
    modifier: Modifier = Modifier,
) {
    //CountingScreen()
    OverviewScreen()
}

@Preview(
    name = "tampilan utama",
    showBackground = true)
@Composable
fun CountingObjectAppPreview() {
    MyCountingObjectTheme {
        CountingObjectApp()
    }
}