package com.example.mycountingobject.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mycountingobject.R
import com.example.mycountingobject.ui.theme.MyCountingObjectTheme

@Composable
fun UserInformation(
    username: String = "User",
    linkPhoto: String,
    modifier: Modifier = Modifier,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ){
        Image(
            painter = rememberAsyncImagePainter(linkPhoto),
            contentDescription = "Photo Profile",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.user_name, username),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = stringResource(id = R.string.greeting),
                color = Color.LightGray,
                fontSize = 15.sp
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewUserInformation(){
    MyCountingObjectTheme() {
        Surface {
            UserInformation(linkPhoto = "https://www.google.co.id/imgres?imgurl=https%3A%2F%2Fwww.pngitem.com%2Fpimgs%2Fm%2F80-800194_transparent-users-icon-png-flat-user-icon-png.png&tbnid=-2Le3C2eveu0yM&vet=12ahUKEwiP29Cby7X_AhW5KrcAHbnAD1EQMygMegUIARD2AQ..i&imgrefurl=https%3A%2F%2Fwww.pngitem.com%2Fmiddle%2FooRhiJ_transparent-users-icon-png-flat-user-icon-png%2F&docid=mOLcoVzi8STohM&w=860&h=1061&q=user&hl=en&authuser=0&ved=2ahUKEwiP29Cby7X_AhW5KrcAHbnAD1EQMygMegUIARD2AQ",
            username = "Siapa")
        }
        
    }
}