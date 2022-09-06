package com.debduttapanda.idealapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.debduttapanda.idealapp.R
import com.debduttapanda.idealapp.Toaster
import com.debduttapanda.idealapp.forward
import com.debduttapanda.idealapp.viewmodels.HomeViewModel
import com.debduttapanda.idealapp.viewmodels.HomeViewModelImpl

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    vm: HomeViewModel = hiltViewModel<HomeViewModelImpl>()
) {
    val owner = LocalLifecycleOwner.current
    val context = LocalContext.current
    LaunchedEffect(key1 = vm.navigation.value){
        vm.navigation.forward(navController,owner, Toaster(context))
    }
    LaunchedEffect(key1 = true){
        vm.onStart()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.tasks))
                },
                actions = {
                    Column(){
                        IconButton(
                                onClick = vm.onMenuClick
                                ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Menu"
                            )
                        }
                        DropdownMenu(
                            expanded = vm.menuExpanded.live.value,
                            onDismissRequest = { vm.menuExpanded.value = false },

                        ) {
                            Column(){
                                DropdownMenuItem(
                                    onClick = {
                                        vm.menuExpanded.value = false
                                        vm.onDeleteAllClick()
                                    }
                                ) {
                                    Text(stringResource(R.string.delete_all))
                                }
                            }
                        }
                    }

                }
            )
        },
    ) {
        Box(){
            if(vm.tasks.live.value.isEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        stringResource(R.string.no_task_yet),
                        style = TextStyle(
                            color = Color.Gray
                        ),
                        modifier = Modifier.semantics {
                            contentDescription = "no_items_yet"
                        }
                    )
                }
            }
            else{
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ){
                    items(
                        key = {
                            vm.tasks.live.value[it].uid
                        },
                        count = vm.tasks.live.value.size
                    ){
                        TaskItemView(vm.tasks.live.value[it],vm = vm)
                    }
                }
            }
            FloatingActionButton(
                onClick = vm.onAddClick,
                modifier = Modifier
                    .semantics {
                        contentDescription = "add_button"
                    }
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
    if(vm.loading.value){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { }
                .background(Color.White.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }
    if(vm.dialoger.enabled.value){
        Dialog(
            onDismissRequest = {
                vm.dialoger.onDone("dismiss")
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ){
                    Text(stringResource(R.string.delete))
                    Divider()
                    Text(stringResource(R.string.are_you_sure_to_delete_q))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ){
                        TextButton(onClick = {
                            vm.dialoger.onDone("positive")
                        }) {
                            Text(stringResource(R.string.yes))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        TextButton(onClick = {
                            vm.dialoger.onDone("negative")
                        }) {
                            Text(stringResource(R.string.cancel))
                        }
                    }
                }
            }
        }
    }
}


