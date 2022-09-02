package com.debduttapanda.core.use_cases

import com.debduttapanda.core.SimpleResource
import com.debduttapanda.core.models.Task
import kotlinx.coroutines.flow.Flow

interface AddTaskUseCase {
    operator fun invoke(task: Task): Flow<SimpleResource>
}