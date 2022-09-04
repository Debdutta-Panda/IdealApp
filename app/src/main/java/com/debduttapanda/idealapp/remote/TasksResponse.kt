package com.debduttapanda.idealapp.remote

import com.debduttapanda.core.models.Task

data class TasksResponse (
    val success: Boolean,
    val message: String,
    val tasks: List<Task>
)