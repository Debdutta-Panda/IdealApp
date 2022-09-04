package com.debduttapanda.core.use_cases.impl

import com.debduttapanda.core.Resource
import com.debduttapanda.core.SimpleResource
import com.debduttapanda.core.models.Task
import com.debduttapanda.core.repository.TaskRepository
import com.debduttapanda.core.use_cases.DeleteAllTaskUseCase
import com.debduttapanda.core.use_cases.DeleteTaskUseCase
import com.debduttapanda.core.use_cases.UpdateTaskUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteAllTaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
) : DeleteAllTaskUseCase {
    override fun invoke(): Flow<SimpleResource> = flow{
        try {
            taskRepository.deleteAllTask()
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message?:""))
        }
    }
}