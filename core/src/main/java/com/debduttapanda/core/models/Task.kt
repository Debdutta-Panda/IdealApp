package com.debduttapanda.core.models

data class Task(
    val uid: Int = 0,
    val title: String,
    val description: String,
    val completed: Boolean
)
