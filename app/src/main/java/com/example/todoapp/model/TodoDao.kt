package com.example.todoapp.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo: Todo)

    /*@Query("SELECT * FROM todo")
    fun selectAllTodo(): List<Todo>*/

    @Query("SELECT * FROM todo WHERE uuid = :id")
    fun selectTodo(id:Int): Todo

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE is_done = 0")
    fun getUndoneTodos(): List<Todo>

    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid=:id")
    suspend fun update(title:String, notes:String, priority:Int, id:Int)

    @Update
    suspend fun updateTodoStatus(todo:Todo)

    @Delete
    fun deleteTodo(todo:Todo)
}