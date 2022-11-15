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
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val getAllTaskUseCase: GetAllTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val deleteAllTaskUseCase: DeleteAllTaskUseCase
): HomeViewModel() {
    override val loading = mutableStateOf(false)
    override val menuExpanded = Value(false)
    override val dialoger = Dialoger()
    override val navigation = mutableStateOf<UIScope?>(null)
    override val tasks = Value<List<Task>>(emptyList())

    override val onDeleteAllClick: () -> Unit = {
        launchCancelable { cancel->
            val result = dialoger.show()
            if(result=="positive"){
                loading.value = true
                deleteAllTaskUseCase()
                    .onEach {
                        if(it is Resource.Error){
                            navigation.scope { navHostController, lifecycleOwner, toaster ->
                                toaster?.toast(it.message?:"")
                            }
                        }
                        else{
                            fetchTasks()
                        }
                        loading.value = false
                        cancel()
                    }
                    .collect()
            }
        }
    }
    override val onMenuClick: () -> Unit = {
        menuExpanded.value = true
    }
    override fun onStart() {
        fetchTasks()
    }

    private fun fetchTasks() {
        loading.value = true
        flower(getAllTaskUseCase()){t,cancel->
            if(t is Resource.Success){
                tasks.value = t.data?: emptyList()
            }
            else if(t is Resource.Error){
                navigation.scope{ navHostController, lifecycleOwner, toaster ->
                    toaster?.toast(t.message?:"")
                }
            }
            loading.value = false
            cancel()
        }

        /*viewModelScope.launch {
            getAllTaskUseCase()
                .onEach {
                    if(it is Resource.Success){
                        tasks.value = it.data?: emptyList()
                    }
                    else if(it is Resource.Error){
                        navigation.scope{ navHostController, lifecycleOwner, toaster ->
                            toaster?.toast(it.message?:"")
                        }
                    }
                    loading.value = false
                    //throw CancellationException()
                }
                .collect()
        }*/
    }

    override fun onDeleteTaskClick(task: Task) {
        launchCancelable {cancel->
            val result = dialoger.show()
            if(result=="positive"){
                loading.value = true
                deleteTaskUseCase(task)
                    .onEach {
                        if(it is Resource.Error){
                            navigation.scope{ navHostController, lifecycleOwner, toaster ->
                                toaster?.toast(it.message?:"")
                            }
                        }
                        else{
                            fetchTasks()
                        }
                        loading.value = false
                        cancel()
                    }
                    .collect()
            }
        }
    }

    override fun onItemClick(task: Task) {
        navigation.scope{ navHostController, lifecycleOwner, toaster ->
            navHostController
                .navigate("${Routes.edit}/${task.uid}")
        }
    }

    override val onTaskCheckChanged: ((Task, Boolean) -> Unit) = { task, checked->
        launchCancelable {cancel->
            updateTaskUseCase(task.copy(completed = checked))
            .onEach {
                fetchTasks()
                cancel()
            }
            .collect()
        }
    }
    override val onAddClick = {
        navigation.scope{ navHostController, lifecycleOwner, toaster ->
            navHostController
                .navigate(Routes.add)

        }
    }
    init {

    }
}