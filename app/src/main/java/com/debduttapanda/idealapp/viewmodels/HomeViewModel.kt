package com.debduttapanda.idealapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.debduttapanda.idealapp.Routes
import com.debduttapanda.idealapp.UIScope
import com.debduttapanda.idealapp.room.TaskDao
import com.debduttapanda.idealapp.scope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var dao: TaskDao
): ViewModel() {
    val onAddClick = {
        navigation.scope{ navHostController, lifecycleOwner, toaster ->
            navHostController.navigate(Routes.add)
        }
    }
    val navigation = mutableStateOf<UIScope?>(null)
    init {
        navigation.scope{ navHostController, lifecycleOwner, toaster ->
            delay(4000L)
            navHostController.popBackStack()
            navHostController.navigate(Routes.home)
        }
    }
}