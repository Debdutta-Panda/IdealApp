package com.debduttapanda.idealapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.debduttapanda.core.models.Task
import com.debduttapanda.idealapp.viewmodels.HomeViewModel
import com.debduttapanda.idealapp.viewmodels.HomeViewModelImpl

@Composable
fun TaskItemView(
    task: Task,
    vm: HomeViewModel = hiltViewModel<HomeViewModelImpl>()
) {
    Card(
        modifier = Modifier
            .semantics {
                contentDescription = "task_item${task.uid}"
            }
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                checked = task.completed, 
                onCheckedChange = {
                    vm.onTaskCheckChanged(task,it)
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable {
                        vm.onItemClick(task)
                    }
            ){
                Text(
                    task.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
                if(task.description.isNotEmpty()){
                    Text(task.description)
                }
            }
            IconButton(
                onClick = {
                    vm.onDeleteTaskClick(task)
                },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
}