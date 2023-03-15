package com.route.todoappc_37.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.route.todoappc_37.database.Dao.TodoDao
import com.route.todoappc_37.database.model.Converters
import com.route.todoappc_37.database.model.Todo

@Database(entities = [Todo::class], version = 1)
@TypeConverters(Converters::class)
abstract class TodosDataBase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao

    companion object {
        var INSTANCE: TodosDataBase? = null
        fun getInstance(context: Context): TodosDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    TodosDataBase::class.java,
                    "TodoDB"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE!!
        }


    }

}