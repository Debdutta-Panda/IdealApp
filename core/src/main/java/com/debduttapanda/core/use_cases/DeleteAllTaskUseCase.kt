package com.debduttapanda.core.use_cases

import com.debduttapanda.core.Resource
import com.debduttapanda.core.SimpleResource
import com.debduttapanda.core.models.Task
import kotlinx.coroutines.flow.Flow

interface DeleteAllTaskUseCase {
    operator fun invoke(): Flow<SimpleResource>
}