package com.debduttapanda.idealapp.remote

import com.debduttapanda.core.models.Task
import retrofit2.Response
import retrofit2.http.*

interface TaskApi {

    @GET("tasks")
    suspend fun getTasks(): Response<TasksResponse>

    @GET("tasks")
    suspend fun getByIds(
        @Query("ids") ids: String
    ): Response<TasksResponse>

    @GET("tasks")
    suspend fun getByTitle(
        @Query("title") title: String
    ): Response<TasksResponse>

    @POST("tasks")
    suspend fun createTasks(
        @Body tasks: List<Task>
    ): Response<SimpleResponse>

    @DELETE("tasks")
    suspend fun deleteTask(
        @Query("ids") id: Int
    ): Response<SimpleResponse>

    @DELETE("tasks")
    suspend fun deleteAll(
        @Query("all") all: Boolean = true
    ): Response<SimpleResponse>

    @PATCH("tasks")
    suspend fun updateTask(
        @Body task: Task
    ): Response<SimpleResponse>
}