package com.debduttapanda.idealapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.debduttapanda.core.models.Task

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    var uid : Int? = null,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "completed") val completed: Boolean?
){
    val task: Task
    get() = Task(
        uid = uid?:0,
        title = title?:"",
        description = description?:"",
        completed = completed?:false
    )
}