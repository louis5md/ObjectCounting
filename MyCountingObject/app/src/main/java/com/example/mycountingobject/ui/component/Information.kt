package com.example.mycountingobject.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycountingobject.ui.theme.LightBlue
import com.example.mycountingobject.ui.theme.MyCountingObjectTheme
import com.example.mycountingobject.ui.theme.Shapes

@Composable
fun MyInformation(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Column(
            modifier = modifier
                .wrapContentHeight(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                color = Color.DarkGray,
                fontSize = 18.sp
            )
            Text(
                text = value,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun MyBigInformation(
    title: String,
    value: Int,
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = LightBlue, shape = Shapes.medium)

    ){
        Column(
            modifier = modifier
                .wrapContentHeight()
                .padding(10.dp)
            ,
           horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 18.sp
            )
            Text(
                text = "$value Pcs",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun previewInformation(){
    MyCountingObjectTheme() {
        Surface() {
            MyBigInformation(title = "Objek Terhitung", value = 187 )
        }
    }
}