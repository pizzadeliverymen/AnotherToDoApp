package me.peders.anothertodoapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID


@Entity(tableName = "todo")
data class ToDo(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val title: String = "",
    val updated: Date = Date()
) {
    @Ignore
    var entries = listOf(ToDoItem(listID = id))
}

@Entity(tableName = "todo_item")
data class ToDoItem(
    @PrimaryKey @ColumnInfo(name="todoItemID") val id: UUID = UUID.randomUUID(),
    val title: String = "",
    val description: String = "",
    val created: Date = Date(),
    val listID:UUID
)
