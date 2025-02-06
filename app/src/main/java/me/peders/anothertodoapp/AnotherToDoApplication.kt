package me.peders.anothertodoapp
import android.app.Application

class AnotherToDoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ToDoRepository.initialize(this)
    }

}