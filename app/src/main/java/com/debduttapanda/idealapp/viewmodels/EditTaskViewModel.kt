package com.debduttapanda.idealapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.debduttapanda.core.Resource
import com.debduttapanda.core.models.Task
import com.debduttapanda.core.use_cases.GetTaskUseCase
import com.debduttapanda.core.use_cases.UpdateTaskUseCase
import com.debduttapanda.idealapp.UIScope
import com.debduttapanda.idealapp.Value
import com.debduttapanda.idealapp.flower
import com.debduttapanda.idealapp.scope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private var getTaskUseCase: GetTaskUseCase,
    private var updateTaskUseCase: UpdateTaskUseCase
): ViewModel() {
    private var _task: Task? = null
    val canUpdate = Value(false)
    val onUpdateClick: ()->Unit = {
        update()
    }

    private fun update() {
        flower(updateTaskUseCase(getTask())){ t, cancel->
            if(t is Resource.Error){
                navigation.scope { navHostController, lifecycleOwner, toaster ->
                    toaster?.toast(t.message?:"")
                }
            }
            goBack()
            cancel()
        }
    }

    private fun getTask(): Task {
        return Task(
            title = title.value,
            description = description.value,
            uid = _task?.uid?:0,
            completed = _task?.completed?:false
        )
    }

    private fun goBack() {
        navigation.scope{ navHostController, lifecycleOwner, toaster ->
            navHostController.popBackStack()
        }
    }

    val description = Value(""){
        onInputChange()
    }
    val title = Value(""){
        onInputChange()
    }

    private fun onInputChange() {
        canUpdate.value = title.value!=_task?.title || description.value!=_task?.description
    }

    fun onTaskIdObtained(taskId: Int) {
        fetchTask(taskId)
    }

    private fun fetchTask(taskId: Int) {
        flower(getTaskUseCase(taskId)){t,cancel->
            if(t is Resource.Success){
                _task = t.data
                if(_task==null){
                    navigation.scope{ navHostController, lifecycleOwner, toaster ->
                        navHostController.popBackStack()
                    }
                    return@flower
                }
                title.value = _task?.title?:""
                description.value = _task?.description?:""
            }
            else if(t is Resource.Error){
                navigation.scope { navHostController, lifecycleOwner, toaster ->
                    toaster?.toast(t.message?:"")
                }
            }
            cancel()
        }
    }

    val navigation = mutableStateOf<UIScope?>(null)
    val onBackClick = {
        navigation.scope{ navHostController, lifecycleOwner, toaster ->
            navHostController.popBackStack()
        }
    }
}