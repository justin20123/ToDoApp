package com.example.todoapp.model

import androidx.room.*

@Dao
interface TodoDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    fun selectTodo(id:Int): Todo

    @Query("SELECT * FROM todo WHERE is_done = 0")
    fun selectNotDone(): List<Todo>

    @Delete
    fun deleteTodo(todo: Todo)

    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid=:id")
    fun update(title:String, notes:String, priority:Int, id:Int)

    @Query("UPDATE todo SET is_done = 1 WHERE uuid=:id")
    fun setDone(id:Int)
}