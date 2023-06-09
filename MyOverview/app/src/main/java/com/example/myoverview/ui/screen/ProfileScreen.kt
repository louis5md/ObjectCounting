package com.example.myoverview.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myoverview.ui.theme.MyOverviewTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
){
    Box(modifier = modifier.fillMaxSize()){
        Text(
            text = "Profile screen has not been implemented",
            modifier = Modifier.align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    MyOverviewTheme() {
        ProfileScreen()
    }
}