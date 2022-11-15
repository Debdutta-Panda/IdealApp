package com.debduttapanda.idealapp

import com.debduttapanda.core.Resource
import com.debduttapanda.core.models.Task
import com.debduttapanda.core.use_cases.GetAllTaskUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FakeGetAllTaskUseCaseImpl : GetAllTaskUseCase {

    private val fakeFlow = MutableSharedFlow<Resource<List<Task>>>()

    suspend fun emit(value: Resource<List<Task>>) = fakeFlow.emit(value)

    override fun invoke(): Flow<Resource<List<Task>>> = fakeFlow
}