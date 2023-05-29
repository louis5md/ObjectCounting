package com.example.mycountingobject

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycountingobject.ui.component.MyCamera
import com.example.mycountingobject.ui.component.MyDropDownMenu
import com.example.mycountingobject.ui.component.MyOneLineRadioButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CountingObjectApp(
    modifier: Modifier = Modifier,
    dataStoreUtil: DataStoreUtil,
){
    var isCounting by remember { mutableStateOf(false) }
    var objectCounted by remember { mutableStateOf(0) }
    var optionSelected by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    // Create a list of machine
    val listMachine = stringArrayResource(id = R.array.list_machine).toList()

    // Create a string value to store the selected machine
    var mSelectedText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .wrapContentHeight(Alignment.Top)
            .padding(bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MyDropDownMenu(
            listItem = listMachine,
            onClick = { mSelectedText = it },
            onValueChange = {mSelectedText = it},
            selectedText = mSelectedText,
            modifier = modifier,
        )
        MyOneLineRadioButton(
            listOptions = listOf("IN","OUT","REJECT"),
            optionSelected = optionSelected,
            onClick = {optionSelected = it},
        )
        MyCamera(
            isCounting= isCounting,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f),
            onCount = {
                objectCounted++
            }
        )
        Text(text = stringResource(id = R.string.button_counting, objectCounted))
        Button(
            onClick = {
                if (permissionState.status is PermissionStatus.Denied){
                    permissionState.launchPermissionRequest()
                } else {
                    isCounting = !isCounting
                }
                coroutineScope.launch {
                    dataStoreUtil.saveTheme(isCounting)
                }
            },
        ) {
            Text(text = if(!isCounting) "Start Counting" else "Stop Counting")
        }
    }
}