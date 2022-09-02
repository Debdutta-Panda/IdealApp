package com.debduttapanda.core.use_cases

import com.debduttapanda.core.Resource
import com.debduttapanda.core.SimpleResource
import com.debduttapanda.core.models.Task
import kotlinx.coroutines.flow.Flow

interface GetAllTaskUseCase {
    operator fun invoke(): Flow<Resource<List<Task>>>
}