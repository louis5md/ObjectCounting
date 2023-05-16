package com.example.myoverview.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myoverview.R
import com.example.myoverview.model.Machine
import com.example.myoverview.ui.theme.MyOverviewTheme

@Composable
fun ItemMachine(
    data: Machine,
    modifier: Modifier = Modifier,
){
    Column(
        modifier
            .wrapContentHeight()
            .width(IntrinsicSize.Min)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = Color.Cyan,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)) {
        Text(text = data.name, fontSize = 20.sp, overflow = TextOverflow.Ellipsis, modifier = modifier.align(
            Alignment.CenterHorizontally
        ))
        Text(text = stringResource(id = R.string.machine_overview_in, data.IN), fontSize = 16.sp)
        Text(text = stringResource(id = R.string.machine_overview_out, data.OUT), fontSize = 16.sp)
        Text(text = stringResource(id = R.string.machine_overview_reject, data.REJECT), fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ItemMachinePreview(){
    MyOverviewTheme {
        val data = Machine("Mesin 1", 10, 10, 0)
        ItemMachine(data = data)
    }
}