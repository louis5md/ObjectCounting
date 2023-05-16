package com.example.mycountingobject.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize


@Composable
fun MyDropDownMenu(
    modifier: Modifier = Modifier,
    isExpanded : Boolean = false,
    text : String,
    listItem : List<String>,
    onDismissRequest : () -> Unit,
    onClick : (String) -> Unit,
    onValueChange : (String) -> Unit,
    onIconClick : () -> Unit,
) {
    val icon = if (isExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    Column(
        modifier = modifier
            .padding(20.dp)
            .wrapContentHeight(Alignment.Top),
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {Text("Mesin")},
            trailingIcon = {
                IconButton(onClick = {onIconClick()}){
                    Icon(imageVector = icon, contentDescription = "memunculkan drop down menu")
                }
            }
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { onDismissRequest()},
            modifier = Modifier.width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            listItem.forEach { label ->
                DropdownMenuItem(onClick = { onClick(label) })
                {
                    Text(text = label)
                }
            }
        }
    }
}