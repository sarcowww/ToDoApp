package com.sarco.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sarco.todoapp.data.models.ToDoData

@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    companion object {

        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getDatabase(context: Context): ToDoDatabase {
            val tempInstace = INSTANCE
            if(tempInstace != null){
                return tempInstace
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}