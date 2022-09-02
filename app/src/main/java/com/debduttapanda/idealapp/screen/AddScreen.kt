package com.debduttapanda.idealapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.debduttapanda.idealapp.R
import com.debduttapanda.idealapp.forward
import com.debduttapanda.idealapp.viewmodels.AddTaskViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddScreen(
    navController: NavHostController,
    vm: AddTaskViewModel = hiltViewModel()
){
    val owner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = vm.navigation.value){
        vm.navigation.forward(navController,owner)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.add_task))
                },
                navigationIcon = {
                    IconButton(
                        onClick = vm.onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ){
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = vm.title.live.value,
                onValueChange = {
                                vm.title.change(it)
                },
                label = {
                    Text(stringResource(R.string.title))
                },
                placeholder = {
                    Text(stringResource(R.string.title))
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = vm.description.live.value,
                onValueChange = {
                    vm.description.change(it)
                },
                label = {
                    Text(stringResource(R.string.description))
                },
                placeholder = {
                    Text(stringResource(R.string.description))
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = vm.onAddClick,
                enabled = vm.canAdd.live.value
            ){
                Text(stringResource(R.string.add))
            }
        }
    }

}