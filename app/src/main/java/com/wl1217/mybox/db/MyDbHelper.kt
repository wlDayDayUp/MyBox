package com.wl1217.mybox.db

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, "test.db", null, 1) {

    companion object {
        const val CREATE_TABLE_USER =
            "CREATE TABLE IF NOT EXISTS user ( " +
                    "person_id INTEGER primary key autoincrement, " +
                    "name VARCHAR(32), " +
                    "age INTEGER" +
                    ")"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) {
            db?.execSQL(
                "Alter table user ADD phone VARCHAR(11)"
            )
        }
    }

}