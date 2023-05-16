package com.example.mycountingobject

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycountingobject.ui.component.MyCamera
import com.example.mycountingobject.ui.component.MyDropDownMenu
import com.example.mycountingobject.ui.component.MyOneLineRadioButton
import com.example.mycountingobject.ui.theme.MyCountingObjectTheme


@Composable
fun CountingObjectApp(
    modifier: Modifier = Modifier,
){
    var isExpanded by remember { mutableStateOf(false) }
    var isCounting by remember { mutableStateOf(false) }
    val objectCounted by remember { mutableStateOf(0) }
    var optionSelected by remember { mutableStateOf("") }

    // Create a list of machine
    val listMachine = stringArrayResource(id = R.array.list_machine).toList()

    // Create a string value to store the selected machine
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
            onClick = {optionSelected = it},
        )
        Button(
            onClick = { isCounting = !isCounting },
        ) {
            Text(text = if(!isCounting) "Start Counting" else "Stop Counting")
        }
        Text(text = stringResource(id = R.string.button_counting, objectCounted))
        MyCamera(modifier = Modifier.padding(50.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CountingObjectAppPreview() {
    MyCountingObjectTheme {
        CountingObjectApp()
    }
}