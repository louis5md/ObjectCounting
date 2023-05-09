package com.example.mycountingobject.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycountingobject.R
import com.example.mycountingobject.component.MyCamera
import com.example.mycountingobject.component.MyDropDownMenu
import com.example.mycountingobject.component.MyOneLineRadioButton

@Composable
fun CountingScreen(
    modifier: Modifier = Modifier,
){
    var isExpanded by remember { mutableStateOf(false) }
    var isCounting by remember { mutableStateOf(false) }
    var objectCounted by remember { mutableStateOf(0) }
    var optionSelected by remember { mutableStateOf("") }

    // Create a list of cities
    val listMachine = stringArrayResource(id = R.array.list_machine).toList()

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .wrapContentHeight(Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MyDropDownMenu(
            isExpanded = isExpanded,
            listItem = listMachine,
            onDismissRequest = {isExpanded = false },
            onClick = {
                mSelectedText = it
                isExpanded = false
            },
            onValueChange = {mSelectedText = it},
            text = mSelectedText,
            onIconClick = {isExpanded = !isExpanded},
            modifier = modifier,
        )
        MyOneLineRadioButton(
            listOptions = listOf("IN","OUT","REJECT"),
            optionSelected = optionSelected,
            onClick = {optionSelected = it},)
        Button(
            onClick = { isCounting = !isCounting },
        ) {
            Text(text = if(!isCounting) "Start Counting" else "Stop Counting")
        }
        Text(text = stringResource(id = R.string.button_counting, objectCounted))
        MyCamera(modifier = Modifier.padding(20.dp))
    }
}