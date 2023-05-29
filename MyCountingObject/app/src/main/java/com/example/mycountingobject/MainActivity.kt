package com.example.mycountingobject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.mycountingobject.ui.theme.MyCountingObjectTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var dataStoreUtil: DataStoreUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStoreUtil = DataStoreUtil(applicationContext)
        CoroutineScope(Dispatchers.Default).launch {
            dataStoreUtil.saveTheme(false)
        }
        setContent {
            MyCountingObjectTheme(darkTheme = dataStoreUtil.getTheme(false).collectAsState(initial = false).value) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CountingObjectApp(dataStoreUtil = dataStoreUtil)
                }
            }
        }
    }
}