package com.wl1217.mybox.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.wl1217.mybox.bean.User

class UserDao(
    val db: SQLiteDatabase
) {

    companion object {
        const val TABLE_NAME_USER = "user"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
    }

    /**
     * 查询所有
     */
    fun queryAll(): ArrayList<User> {
        val users = arrayListOf<User>()
        val queryAllCursor = db.query(
            TABLE_NAME_USER, null, null, null, null, null, null
        )
        while (queryAllCursor.moveToNext()) {
            users.add(
                User(
                    queryAllCursor.getString(queryAllCursor.getColumnIndex(COLUMN_NAME)),
                    queryAllCursor.getInt(queryAllCursor.getColumnIndex(COLUMN_AGE))
                )
            )
        }
        queryAllCursor.close()
        return users
    }

    /**
     * 添加User
     */
    fun addUser(user: User): Boolean {
        val userContent = ContentValues()
        userContent.put(COLUMN_NAME, user.name)
        userContent.put(COLUMN_AGE, user.age)
        val insertResult = db.insert(
            TABLE_NAME_USER,
            null,
            userContent
        )
        return insertResult != -1L
    }

    /**
     * 根据名称删除
     */
    fun deleteByName(name: String): Boolean {
        val deleteResult = db.delete(
            TABLE_NAME_USER,
            "$COLUMN_NAME=?",
            arrayOf(name)
        )
        return deleteResult != 0
    }

    /**
     * 更新
     */
    fun upDateByName(newName: String, oldName: String): Boolean {
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, newName)
        val updateResult = db.update(
            TABLE_NAME_USER,
            contentValues,
            "$COLUMN_NAME=?",
            arrayOf(oldName)
        )
        return updateResult != 0
    }
}