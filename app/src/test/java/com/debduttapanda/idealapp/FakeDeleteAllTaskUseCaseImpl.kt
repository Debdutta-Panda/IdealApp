package com.debduttapanda.idealapp

import com.debduttapanda.core.SimpleResource
import com.debduttapanda.core.models.Task
import com.debduttapanda.core.use_cases.DeleteAllTaskUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeDeleteAllTaskUseCaseImpl: DeleteAllTaskUseCase {
    private val fakeFlow = MutableSharedFlow<SimpleResource>()

    suspend fun emit(value: SimpleResource) = fakeFlow.emit(value)

    override fun invoke(): Flow<SimpleResource> = fakeFlow
}