package com.example.mycountingobject.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MyOneLineRadioButton(
    listOptions: List<String>,
    optionSelected: String,
    onClick: (String)->Unit,
    modifier: Modifier = Modifier,
){
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly){
        listOptions.forEach {text->
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .selectable(
                        selected = text == optionSelected,
                        onClick = {
                            onClick(text)
                        }
                    )
                    .align(Alignment.CenterVertically),
            ){
                RadioButton(
                    selected = text == optionSelected,
                    onClick = { onClick(text) },
                    modifier = modifier.align(Alignment.CenterVertically))
                Text(text = text,modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
    }

}