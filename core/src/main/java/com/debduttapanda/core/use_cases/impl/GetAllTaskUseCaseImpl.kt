package com.debduttapanda.core.use_cases.impl

import com.debduttapanda.core.Resource
import com.debduttapanda.core.entity.TaskEntity
import com.debduttapanda.core.entity.TaskEntityImpl
import com.debduttapanda.core.models.Task
import com.debduttapanda.core.use_cases.GetAllTaskUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllTaskUseCaseImpl @Inject constructor(
    private val taskEntity: TaskEntity
) : GetAllTaskUseCase {
    override fun invoke(): Flow<Resource<List<Task>>> = flow{
        try {
            val r = taskEntity.getAllTasks()
            emit(Resource.Success(r))
        } catch (e: Exception) {
            emit(Resource.Error(e.message?:""))
        }

    }

}