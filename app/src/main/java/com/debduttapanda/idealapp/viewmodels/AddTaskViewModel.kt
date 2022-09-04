package com.debduttapanda.idealapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debduttapanda.core.Resource
import com.debduttapanda.core.models.Task
import com.debduttapanda.core.use_cases.AddTaskUseCase
import com.debduttapanda.idealapp.UIScope
import com.debduttapanda.idealapp.Value
import com.debduttapanda.idealapp.flower
import com.debduttapanda.idealapp.scope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private var addTaskUseCase: AddTaskUseCase
): ViewModel() {
    val canAdd = Value(false)
    val onAddClick: ()->Unit = {
        flower(addTaskUseCase.invoke(
            Task(
                title = title.value,
                description = description.value,
                completed = false,
            )
        )){t,cancel->
            navigation.scope{ navHostController, lifecycleOwner, toaster ->
                if(t is Resource.Error){
                    toaster?.toast(t.message?:"")
                }
                navHostController.popBackStack()
            }
            cancel()
        }
    }
    val description = Value(""){
        onInputChange()
    }
    val title = Value(""){
        onInputChange()
    }

    private fun onInputChange() {
        canAdd.value = title.value.isNotEmpty()/* && description.value.isNotEmpty()*/
    }

    val navigation = mutableStateOf<UIScope?>(null)
    val onBackClick = {
        navigation.scope{ navHostController, lifecycleOwner, toaster ->
            navHostController.popBackStack()
        }
    }
}