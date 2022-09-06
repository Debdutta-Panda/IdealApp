package com.debduttapanda.core.use_cases.impl

import android.util.Log
import com.debduttapanda.core.Resource
import com.debduttapanda.core.SimpleResource
import com.debduttapanda.core.entity.TaskEntity
import com.debduttapanda.core.entity.TaskEntityImpl
import com.debduttapanda.core.models.Task
import com.debduttapanda.core.repository.TaskRepository
import com.debduttapanda.core.use_cases.AddTaskUseCase
import com.debduttapanda.core.use_cases.GetAllTaskUseCase
import com.debduttapanda.core.use_cases.GetTaskUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTaskUseCaseImpl @Inject constructor(
    private val taskEntity: TaskEntity
) : GetTaskUseCase {
    override fun invoke(taskId: Int): Flow<Resource<Task?>> = flow{
        try {
            val r = taskEntity.getTaskById(taskId)
            emit(Resource.Success(r))
        } catch (e: Exception) {
            emit(Resource.Error(e.message?:""))
        }
    }
}