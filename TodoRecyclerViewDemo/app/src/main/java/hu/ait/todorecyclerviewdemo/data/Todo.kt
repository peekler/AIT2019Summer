package hu.ait.todorecyclerviewdemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todotable")
data class Todo (
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "createdate") var createDate: String,
    @ColumnInfo(name = "done") var done: Boolean,
    @ColumnInfo(name = "todotext") var todoText: String
)