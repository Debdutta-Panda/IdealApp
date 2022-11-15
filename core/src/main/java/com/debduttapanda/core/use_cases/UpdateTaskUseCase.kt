package com.debduttapanda.core.use_cases

import com.debduttapanda.core.Resource
import com.debduttapanda.core.SimpleResource
import com.debduttapanda.core.models.Task
import kotlinx.coroutines.flow.Flow

interface UpdateTaskUseCase {
    operator fun invoke(task: Task): Flow<SimpleResource>
}

/*
interface UseCase<T,E>{
    operator fun invoke(value: T): Flow<E>
}*/
