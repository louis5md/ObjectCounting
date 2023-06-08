package com.example.mycountingobject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
){

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.logo_evomo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 150.dp)
                    .padding(bottom = 16.dp)
                    .background(Color.Transparent),
            )
            Button(
                onClick = {
                      onClick()
                },
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.DarkGray
                ),
                modifier = Modifier.shadow(6.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_google),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text(text = "Login With Google", modifier = Modifier.padding(6.dp))
            }
        }
    }
}