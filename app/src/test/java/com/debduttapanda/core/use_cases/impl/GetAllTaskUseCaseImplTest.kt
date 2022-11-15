package com.debduttapanda.core.use_cases.impl

import com.debduttapanda.core.entity.TaskEntity
import com.debduttapanda.core.models.Task
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.mockito.kotlin.whenever


class GetAllTaskUseCaseImplTest{
    private lateinit var getAllTaskUseCaseImpl: GetAllTaskUseCaseImpl

    @Before
    fun setup(){
        val taskEntity: TaskEntity = mockk()
        coEvery { taskEntity.getAllTasks() } returns listOf(
            Task(
                title = "title",
                description = "description"
            )
        )
        getAllTaskUseCaseImpl = GetAllTaskUseCaseImpl(taskEntity)
    }

    @Test
    fun test1(){
        runBlocking {
            val a = getAllTaskUseCaseImpl().last()
            assertThat(a.data?.size?:0, greaterThan(0))
        }
    }
}