package me.peders.anothertodoapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import me.peders.anothertodoapp.ToDo
import me.peders.anothertodoapp.ToDoItem
import java.util.UUID

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo t JOIN todo_item i ON i.listID = t.id ORDER BY i.created DESC")
    fun getToDos(): Flow<Map<ToDo, List<ToDoItem>>>

    @Query("SELECT * FROM todo WHERE id=(:id)")
    suspend fun internalGetToDo(id: UUID): ToDo

    @Query("SELECT * FROM todo_item WHERE listID = (:todoID)")
    suspend fun internalGetEntriesForToDo(todoID: UUID): List<ToDoItem>

    @Transaction
    suspend fun getToDoAndEntries(id: UUID): ToDo {
        return internalGetToDo(id).apply {
            entries = internalGetEntriesForToDo(id)
        }
    }

    @Update
    suspend fun internalUpdateToDo(todo: ToDo)

    @Insert
    suspend fun internalInsertToDo(todo: ToDo)

    @Insert
    suspend fun internalInsertToDoItem(toDoItem: ToDoItem)

    @Query("DELETE FROM todo_item WHERE listID = (:listID)")
    suspend fun internalDeleteEntriesFromToDo(listID: UUID)

    @Delete
    suspend fun internalDeleteToDo(todo: ToDo)

    @Transaction
    suspend fun updateToDoAndEntries(todo: ToDo) {
        internalDeleteEntriesFromToDo(todo.id)
        todo.entries.forEach { internalInsertToDoItem(it) }
        internalUpdateToDo(todo)
    }

    @Transaction
    suspend fun insertToDoAndEntries(todo: ToDo) {
        internalInsertToDo(todo)
        todo.entries.forEach { internalInsertToDoItem(it) }
    }

    @Transaction
    suspend fun deleteToDoAndEntries(todo: ToDo) {
        internalDeleteEntriesFromToDo(todo.id)
        internalDeleteToDo(todo)
    }
}