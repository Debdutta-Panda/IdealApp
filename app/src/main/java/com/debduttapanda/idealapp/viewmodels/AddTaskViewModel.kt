package com.debduttapanda.idealapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debduttapanda.core.models.Task
import com.debduttapanda.core.use_cases.AddTaskUseCase
import com.debduttapanda.idealapp.UIScope
import com.debduttapanda.idealapp.Value
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
        viewModelScope.launch/*(Dispatchers.IO)*/ {
            addTaskUseCase.invoke(
                Task(
                    title = title.value,
                    description = description.value,
                    completed = false,
                )
            ).collect()
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