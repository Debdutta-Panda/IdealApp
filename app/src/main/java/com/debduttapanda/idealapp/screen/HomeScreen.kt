package com.debduttapanda.idealapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.debduttapanda.idealapp.forward
import com.debduttapanda.idealapp.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    vm: HomeViewModel = hiltViewModel()
) {
    val owner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = vm.navigation.value){
        vm.navigation.forward(navController,owner)
    }
    Box(){
        LazyColumn(){
            items(
                key = {
                    vm.tasks.live.value[it].uid
                },
                count = vm.tasks.live.value.size
            ){
                Text(vm.tasks.live.value[it].title)
            }
        }
        FloatingActionButton(
            onClick = vm.onAddClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }
    }
}