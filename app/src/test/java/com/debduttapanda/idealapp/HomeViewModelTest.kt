package com.debduttapanda.idealapp

import com.debduttapanda.core.Resource
import com.debduttapanda.core.models.Task
import com.debduttapanda.idealapp.viewmodels.HomeViewModelImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThan
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get: Rule
    val dispatcherRule = ViewModelRule()

    private lateinit var viewModel: HomeViewModelImpl
    private lateinit var fakeGetAllTaskUseCase: FakeGetAllTaskUseCaseImpl
    private lateinit var fakeUpdateTaskUseCase: FakeUpdateTaskUseCaseImpl
    private lateinit var fakeDeleteTaskUseCase: FakeDeleteTaskUseCaseImpl
    private lateinit var fakeDeleteAllTaskUseCase: FakeDeleteAllTaskUseCaseImpl

    @Before
    fun setUp() {
        fakeGetAllTaskUseCase = FakeGetAllTaskUseCaseImpl()
        fakeUpdateTaskUseCase = FakeUpdateTaskUseCaseImpl()
        fakeDeleteTaskUseCase = FakeDeleteTaskUseCaseImpl()
        fakeDeleteAllTaskUseCase = FakeDeleteAllTaskUseCaseImpl()
        viewModel = HomeViewModelImpl(
            fakeGetAllTaskUseCase,
            fakeUpdateTaskUseCase,
            fakeDeleteTaskUseCase,
            fakeDeleteAllTaskUseCase
        )
    }

    @Test
    fun `get all task test`() = runTest {
        viewModel.onStart()
        fakeGetAllTaskUseCase.emit(
            Resource.Success(
                listOf(
                    Task(
                        title = "title",
                        description = "description"
                    )
                )
            )
        )
        assertThat(viewModel.tasks.value.size, greaterThan(0))
    }
}