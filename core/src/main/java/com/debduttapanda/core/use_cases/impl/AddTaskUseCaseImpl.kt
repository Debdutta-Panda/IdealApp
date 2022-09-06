package com.debduttapanda.core.use_cases.impl

import com.debduttapanda.core.Resource
import com.debduttapanda.core.SimpleResource
import com.debduttapanda.core.entity.TaskEntity
import com.debduttapanda.core.entity.TaskEntityImpl
import com.debduttapanda.core.models.Task
import com.debduttapanda.core.repository.TaskRepository
import com.debduttapanda.core.use_cases.AddTaskUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddTaskUseCaseImpl @Inject constructor(
    private val taskEntity: TaskEntity
) : AddTaskUseCase {
    override fun invoke(task: Task): Flow<SimpleResource> = flow {
        try {
            taskEntity.addTask(task)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message?:""))
        }
    }
}