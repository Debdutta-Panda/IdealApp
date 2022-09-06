package com.debduttapanda.idealapp.viewmodels

import com.debduttapanda.core.models.Task
import com.debduttapanda.idealapp.Value

class MockHomeViewModelWithTasks: HomeViewModel() {
    override fun onStart() {

    }
    override val onMenuClick: () -> Unit
        get() = {}
    override val onTaskCheckChanged: (Task, Boolean) -> Unit
        get() = {t,b->}

    override fun onItemClick(task: Task) {

    }

    override fun onDeleteTaskClick(task: Task) {

    }

    override val onAddClick: () -> Unit
        get() = {}
    override val onDeleteAllClick: () -> Unit
        get() = {}
    override val tasks: Value<List<Task>>
        get() = Value(listOf(
            Task(
                uid = 1,
                title = "Title",
                description = "Description",
                completed = false
            )
        ))
}