package hu.ait.todorecyclerviewdemo.data

import androidx.room.*

@Dao
interface TodoDAO {
    @Query("SELECT * FROM todotable")
    fun getAllTodos(): List<Todo>

    @Insert
    fun insertTodo(todo: Todo) : Long

    @Delete
    fun deleteTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)
}