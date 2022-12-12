package com.example.sqlitetest

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import android.widget.Toast

class SQLiteHelper(private val context: Context?):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
    val CREATE_TABLE="create table $TABLE_NAME($FIRST_NAME text,$LAST_NAME text, $USER_NAME text)"
    Toast.makeText(context,"onCreate is called",Toast.LENGTH_LONG).show()
        try {
            db?.execSQL(CREATE_TABLE)
            Toast.makeText(context,"Table is called",Toast.LENGTH_LONG).show()
        }
        catch (e:Exception)
        {
            Toast.makeText(context,"Exception $e",Toast.LENGTH_LONG).show()
        }
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Toast.makeText(context, "onUpgrate call", Toast.LENGTH_LONG).show()
        db?.execSQL("drop table if exists $TABLE_NAME")
        onCreate(db)
    }
    fun insertData(
        firstName:String?,
        lastName:String?,
        userName:String?
    ):Boolean {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(FIRST_NAME, firstName)
        cv.put(LAST_NAME, lastName)
        cv.put(USER_NAME, userName)
        val result = db.insert(TABLE_NAME, null, cv)
        return result != -1L
    }
    fun showAll():Cursor
    {
        val select= "Select * from TABLE_NAME"
        val db=readableDatabase
        return db.rawQuery(select,null)
    }
    companion object
    {
        private const val DATABASE_NAME="SQLite_db"
        private const val DATABASE_VERSION=2
        private const val TABLE_NAME="Student"
        private const val FIRST_NAME="firstName"
        private const val LAST_NAME="lastName"
        private const val USER_NAME="userName"
    }
    init {
        Toast.makeText(context,"constructor is called",Toast.LENGTH_LONG).show()
    }
}


