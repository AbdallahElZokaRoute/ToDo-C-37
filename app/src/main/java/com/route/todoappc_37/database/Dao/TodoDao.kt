package com.route.todoappc_37.database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.route.todoappc_37.database.model.Todo
import java.util.Date

//ORM
@Dao
interface TodoDao {
    @Insert
    fun insertTodo(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Query("SELECT * FROM todoTable")
    fun getTodos(): List<Todo>

    @Query("Select * from todoTable where date = :date")
    fun getTodosByDate(date: Date): List<Todo>
    @Query("UPDATE todoTable SET isDone = :isDone WHERE id = :id")
    fun isDoneUpdate(isDone : Boolean , id : Int)


}