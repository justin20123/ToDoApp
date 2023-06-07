package com.example.todoapp.model

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database (entities = [Todo::class], version = 1)
abstract class TodoDatabase:RoomDatabase(){
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile private var instance: TodoDatabase ?= null
        private val LOCK = Any()
        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext, TodoDatabase::class.java, "todoDb")
            .addMigrations(MIGRATION_1_2, MIGRATION_1_3, MIGRATION_2_3).build()
        operator fun invoke(context: Context) {
            if (instance!=null){
                synchronized(LOCK){
                    instance?: buildDb(context).also {
                        instance=it
                    }
                }

            }
        }
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE todo ADD COLUMN priority TEXT")
            }
        }

        val MIGRATION_1_3 = object : Migration(1, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE todo ADD COLUMN priority TEXT")
                database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER")
                database.execSQL("UPDATE todo SET is_done = 0")
            }
        }


        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER")
                database.execSQL("UPDATE todo SET is_done = 0")
            }
        }


    }







}