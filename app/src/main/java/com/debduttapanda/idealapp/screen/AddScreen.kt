package com.debduttapanda.idealapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddScreen(){
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        TextField(
            value =,
            onValueChange =
        )
    }
}