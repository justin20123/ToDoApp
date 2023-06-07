package com.example.todoapp.model

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val DB_NAME = "todoDb"
fun buildDb(context: Context):TodoDatabase {
    val db = Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2, MIGRATION_1_3, MIGRATION_2_3).build()
    return db
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

//val MIGRATION_2_3 = object : Migration(2, 3) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("ALTER TABLE todo ADD COLUMN password TEXT")
//    }
//}
//val MIGRATION_1_3 = object : Migration(1, 3) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("ALTER TABLE todo ADD COLUMN priority INT")
//        database.execSQL("ALTER TABLE todo ADD COLUMN password TEXT")
//    }
//}
