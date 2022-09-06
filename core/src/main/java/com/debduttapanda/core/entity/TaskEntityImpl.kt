package com.debduttapanda.core.entity

import com.debduttapanda.core.models.Task
import com.debduttapanda.core.repository.TaskRepository
import javax.inject.Inject

class TaskEntityImpl @Inject constructor(
    private val taskRepository: TaskRepository
)
    : TaskEntity {
    override suspend fun addTask(task: Task) {
        taskRepository.addTask(task)
    }

    override suspend fun getAllTasks(): List<Task> {
        return taskRepository.getAllTasks()
    }

    override suspend fun updateTask(task: Task) {
        taskRepository.updateTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskRepository.deleteTask(task)
    }

    override suspend fun deleteAllTask() {
        taskRepository.deleteAllTask()
    }

    override suspend fun getTaskById(id: Int): Task? {
        return taskRepository.getTaskById(id)
    }
}