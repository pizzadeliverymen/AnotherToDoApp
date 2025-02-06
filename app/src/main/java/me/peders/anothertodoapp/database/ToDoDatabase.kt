package me.peders.anothertodoapp.database

import androidx.room.Database
import androidx.room.TypeConverters
import me.peders.anothertodoapp.ToDo
import me.peders.anothertodoapp.ToDoItem


@Database(entities = [ToDoItem::class, ToDo::class], version=1)
@TypeConverters(ToDoTypeConverters::class)
abstract class ToDoDatabase {

    abstract fun todoDao(): ToDoDao

}