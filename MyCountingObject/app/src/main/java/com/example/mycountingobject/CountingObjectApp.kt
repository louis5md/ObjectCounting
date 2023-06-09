package com.example.mycountingobject

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycountingobject.ui.component.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CountingObjectApp(
    modifier: Modifier = Modifier,
    username: String,
    photoUrl: String,
){
    var isCounting by remember { mutableStateOf(false) }
    var objectCounted by remember { mutableStateOf(0) }
    var optionSelected by remember { mutableStateOf("") }
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    // Create a list of machine
    val listMachine = stringArrayResource(id = R.array.list_machine).toList()

    // Create a string value to store the selected machine
    var mSelectedText by remember { mutableStateOf("") }
    if(!isCounting){
        BeforeCounting(
            objectCounted = objectCounted,
            optionSelected = optionSelected,
            listMachine = listMachine,
            mSelectedText = mSelectedText,
            onClickMachine = {mSelectedText = it},
            onChangeMachine = {mSelectedText = it},
            onClickParameter = {optionSelected = it},
            onCLickButton = {
                if (permissionState.status is PermissionStatus.Denied){
                    permissionState.launchPermissionRequest()
                } else {
                    isCounting = !isCounting
                }
            },
            username = username,
            photoUrl = photoUrl,
        )
    }else{
        WhileCounting(
            objectCounted = objectCounted,
            optionSelected = optionSelected,
            mSelectedText = mSelectedText,
            onCLickButton = { isCounting = !isCounting },
            onCount = { objectCounted += 1 }
        )
    }
}

@Composable
fun BeforeCounting(
    objectCounted: Int,
    optionSelected: String,
    listMachine: List<String>,
    mSelectedText: String,
    onClickMachine: (String)->Unit,
    onChangeMachine: (String)->Unit,
    onClickParameter: (String)->Unit,
    onCLickButton: ()->Unit,
    modifier: Modifier = Modifier,
    username: String,
    photoUrl: String,
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ){
        Column(
            modifier = Modifier
                .wrapContentHeight(Alignment.Top)
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UserInformation(
                linkPhoto = photoUrl,
                username = username,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
            Text(
                text ="Mesin",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            MyDropDownMenu(
                listItem = listMachine,
                onClick = onClickMachine,
                onValueChange = onChangeMachine,
                selectedText = mSelectedText,
                modifier = Modifier,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text ="Parameter",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            MyOneLineRadioButton(
                listOptions = listOf("IN","OUT","REJECT"),
                optionSelected = optionSelected,
                onClick = onClickParameter,
            )
            MyCamera(
                isCounting= false,
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
                onCount = {

                }
            )
            Text(text = stringResource(id = R.string.button_counting, objectCounted))
            Button(
                enabled = (mSelectedText.isNotEmpty() && optionSelected.isNotEmpty()),
                onClick = onCLickButton,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Text(text = "Start Count", fontSize = 20.sp)
            }
        }
    }

}

@Composable
fun WhileCounting(
    objectCounted: Int,
    optionSelected: String,
    mSelectedText: String,
    onCLickButton: ()->Unit,
    onCount: ()->Unit,
    modifier: Modifier = Modifier,
){
    Box(modifier = modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Column(
            modifier = Modifier
                .wrapContentHeight(Alignment.Top)
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MyInformation(title = "Mesin", value = mSelectedText)
            MyInformation(title = "Parameter", value = optionSelected)
            MyBigInformation(title = "Objek Terhitung", value = objectCounted)
            MyCamera(
                isCounting= true,
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
                onCount = onCount
            )
            Button(
                onClick = onCLickButton,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Text(text = "Stop Counting", fontSize = 20.sp)
            }
        }
    }
}

