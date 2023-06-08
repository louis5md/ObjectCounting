package com.example.mycountingobject.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.toSize


@Composable
fun MyDropDownMenu(
    modifier: Modifier = Modifier,
    selectedText : String,
    listItem : List<String>,
    onClick : (String) -> Unit,
    onValueChange : (String) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    val focusRequester = remember{ FocusRequester() }
    val focusManager = LocalFocusManager.current

    val icon = if (isExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        modifier = modifier
            .wrapContentHeight(Alignment.Top),
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { onValueChange(it) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            label = {Text("Mesin")},
            trailingIcon = {
                IconButton(onClick = { isExpanded = !isExpanded }){
                    Icon(imageVector = icon, contentDescription = "memunculkan drop down menu")
                }
            }
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false},
            modifier = Modifier.width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            listItem.forEach { label ->
                DropdownMenuItem(onClick = {
                    onClick(label)
                    isExpanded = !isExpanded
                })
                {
                    Text(text = label)
                }
            }
        }
    }
}