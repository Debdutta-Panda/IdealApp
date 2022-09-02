package com.debduttapanda.idealapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debduttapanda.core.Resource
import com.debduttapanda.core.models.Task
import com.debduttapanda.core.use_cases.GetAllTaskUseCase
import com.debduttapanda.idealapp.Routes
import com.debduttapanda.idealapp.UIScope
import com.debduttapanda.idealapp.Value
import com.debduttapanda.idealapp.room.TaskDao
import com.debduttapanda.idealapp.scope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTaskUseCase: GetAllTaskUseCase
): ViewModel() {
    val tasks = Value<List<Task>>(emptyList())
    val navigation = mutableStateOf<UIScope?>(null)
    val onAddClick = {
        navigation.scope{ navHostController, lifecycleOwner, toaster ->
            navHostController
                .navigate(Routes.add)

        }
    }
    init {
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
}