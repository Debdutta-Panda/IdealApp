package com.debduttapanda.idealapp.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.debduttapanda.idealapp.room.AppDatabase
import com.debduttapanda.idealapp.room.TaskDao
import com.debduttapanda.idealapp.room.TaskEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*


@RunWith(AndroidJUnit4::class)
class RoomDatabaseImplTest{
    private lateinit var db: AppDatabase
    private lateinit var dao: TaskDao
    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.taskDao()
    }
    @After
    fun closeDb() {
        db.close()
    }
    @Test
    fun writeAndReadLanguage() = runBlocking {
        val task = TaskEntity(
            title = "title",
            description = "description",
            completed = false
        )
        dao.insertAll(task)
        val tasks = dao.getAll()
        assertThat(tasks[0].title, equalTo(task.title))
    }
}