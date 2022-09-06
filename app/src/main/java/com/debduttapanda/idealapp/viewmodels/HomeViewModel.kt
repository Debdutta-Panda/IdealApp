package com.debduttapanda.idealapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.debduttapanda.core.models.Task
import com.debduttapanda.idealapp.Dialoger
import com.debduttapanda.idealapp.UIScope
import com.debduttapanda.idealapp.Value

abstract class HomeViewModel: ViewModel() {
    open val loading = mutableStateOf(false)
    open val menuExpanded = Value(false)
    open val dialoger = Dialoger()
    open val navigation = mutableStateOf<UIScope?>(null)

    abstract  fun onStart()
    abstract val onMenuClick: ()->Unit
    abstract val onTaskCheckChanged: (Task,Boolean)->Unit
    abstract fun onItemClick(task: Task)
    abstract fun onDeleteTaskClick(task: Task)
    abstract val onAddClick: ()->Unit
    abstract val onDeleteAllClick: ()->Unit
    abstract val tasks: Value<List<Task>>
}