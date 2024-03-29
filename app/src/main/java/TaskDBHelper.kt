package com.example.todolist

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TaskDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "TaskDB"
        private const val TABLE_TASKS = "tasks"
        private const val KEY_ID = "id"
        private const val KEY_TITLE = "title"
        private const val KEY_DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_TASKS ($KEY_ID INTEGER PRIMARY KEY, $KEY_TITLE TEXT, $KEY_DESCRIPTION TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TASKS")
        onCreate(db)
    }

    fun addTask(task: Task) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_TITLE, task.title)
        values.put(KEY_DESCRIPTION, task.description)
        db.insert(TABLE_TASKS, null, values)
        db.close()
    }
<<<<<<< HEAD
<<<<<<< HEAD
    fun deleteTask(id: Int): Int {
        val db = this.writableDatabase
        val deletedRows = db.delete(TABLE_TASKS, "$KEY_ID = ?", arrayOf(id.toString()))
        db.close()
        return deletedRows
    }
=======
>>>>>>> 60898ae3102659eee2f263bee7f82e036092da82
=======
>>>>>>> 60898ae3102659eee2f263bee7f82e036092da82

    fun getAllTasks(): ArrayList<Task> {
        val taskList = ArrayList<Task>()
        val selectQuery = "SELECT * FROM $TABLE_TASKS"
        val db = this.readableDatabase
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val title = cursor.getString(cursor.getColumnIndex(KEY_TITLE))
                val description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
                taskList.add(Task(id, title, description))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return taskList
    }
}
