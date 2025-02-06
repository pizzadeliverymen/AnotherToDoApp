package me.peders.anothertodoapp

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import me.peders.anothertodoapp.database.ToDoDatabase


private const val DATABASE_NAME = "todo-database"
class ToDoRepository private constructor(
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope
){

    private val database: ToDoDatabase = Room.databaseBuilder(
        context,
        ToDoDatabase::class.java,
        DATABASE_NAME
    ).createFromAsset(DATABASE_NAME).build()



    companion object {
        private var INSTANCE: ToDoRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ToDoRepository(context)
            }
        }

        fun get(): ToDoRepository {
            return INSTANCE?:
            throw IllegalStateException("ToDoRepository has not been initialized")
        }

    }
}