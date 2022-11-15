package com.debduttapanda.idealapp

import com.debduttapanda.core.SimpleResource
import com.debduttapanda.core.models.Task
import com.debduttapanda.core.use_cases.DeleteTaskUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeDeleteTaskUseCaseImpl: DeleteTaskUseCase {
    private val fakeFlow = MutableSharedFlow<SimpleResource>()

    suspend fun emit(value: SimpleResource) = fakeFlow.emit(value)

    override fun invoke(task: Task): Flow<SimpleResource> = fakeFlow
}