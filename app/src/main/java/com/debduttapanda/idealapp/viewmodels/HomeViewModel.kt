package com.debduttapanda.idealapp.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debduttapanda.core.Resource
import com.debduttapanda.core.models.Task
import com.debduttapanda.core.use_cases.DeleteAllTaskUseCase
import com.debduttapanda.core.use_cases.DeleteTaskUseCase
import com.debduttapanda.core.use_cases.GetAllTaskUseCase
import com.debduttapanda.core.use_cases.UpdateTaskUseCase
import com.debduttapanda.idealapp.*
import com.debduttapanda.idealapp.room.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTaskUseCase: GetAllTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val deleteAllTaskUseCase: DeleteAllTaskUseCase
): ViewModel() {
    val onDeleteAllClick: () -> Unit = {
        viewModelScope.launch {
            val result = dialoger.show()
            if(result=="positive"){
                deleteAllTaskUseCase().collect()
                fetchTasks()
            }
        }
    }
    val menuExpanded = Value(false)
    val onMenuClick: () -> Unit = {
        menuExpanded.value = true
    }
    val dialoger = Dialoger()
    fun onStart() {
        fetchTasks()
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            getAllTaskUseCase()
                .onEach {
                    if(it is Resource.Success){
                        tasks.value = it.data?: emptyList()
                    }
                }
                .collect()
        }
    }

    fun onDeleteTaskClick(task: Task) {
        viewModelScope.launch {
            val result = dialoger.show()
            if(result=="positive"){
                deleteTaskUseCase(task).collect()
                fetchTasks()
            }
        }
    }

    val onTaskCheckChanged: ((Task,Boolean) -> Unit) = {task,checked->
        viewModelScope.launch {
            updateTaskUseCase(task.copy(completed = checked)).collect()
            fetchTasks()
        }
    }
    val tasks = Value<List<Task>>(emptyList())
    val navigation = mutableStateOf<UIScope?>(null)
    val onAddClick = {
        navigation.scope{ navHostController, lifecycleOwner, toaster ->
            navHostController
                .navigate(Routes.add)

        }
    }
    init {

    }
}