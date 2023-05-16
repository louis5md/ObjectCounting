package com.example.myoverview.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myoverview.model.FakeMachine
import com.example.myoverview.ui.component.ItemMachine

@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
){
    val dummyData = FakeMachine.dummyData
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        userScrollEnabled = true,
        modifier = modifier.fillMaxSize()){
        items(dummyData){data->
            ItemMachine(data = data)
        }
    }
}

