package hu.ait.todorecyclerviewdemo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDAO {
    @Query("SELECT * FROM todotable")
    fun getAllTodos(): List<Todo>

    @Insert
    fun insertTodo(vararg todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)
}