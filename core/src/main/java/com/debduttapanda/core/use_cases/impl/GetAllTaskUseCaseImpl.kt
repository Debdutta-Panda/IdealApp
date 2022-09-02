package com.debduttapanda.core.use_cases.impl

import com.debduttapanda.core.Resource
import com.debduttapanda.core.SimpleResource
import com.debduttapanda.core.models.Task
import com.debduttapanda.core.repository.TaskRepository
import com.debduttapanda.core.use_cases.AddTaskUseCase
import com.debduttapanda.core.use_cases.GetAllTaskUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllTaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
) : GetAllTaskUseCase {
    override fun invoke(): Flow<Resource<List<Task>>> = flow{
        emit(Resource.Success(taskRepository.getAllTasks()))
    }

}