package com.example.notesapp


import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQuery
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.CursorAdapter
import android.widget.Toast


    val dbName="myNotes"
    val dbTable="Notes"
    val colId="ID"
    val colTitle="Title"
    val colDesc="Description"
    val dbVersion=1
   


class SqlDatabase(context: Context):SQLiteOpenHelper(context,dbName,null,dbVersion) {



        override fun onCreate(p0: SQLiteDatabase?) {
            val sqlCreateTable = "CREATE TABLE IF NOT EXISTS " + dbTable + " (" + colId + " INTEGER PRIMARY KEY," + colTitle + " TEXT," + colDesc + " TEXT);"

            p0?.execSQL(sqlCreateTable)

        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            p0?.execSQL("if database is exsits" + dbTable)
        }


        fun Insert(values: ContentValues): Long {
            var sqldb:SQLiteDatabase=this.writableDatabase
            var It = sqldb.insert(dbTable, null, values)
            return It!!.toLong()
        }
        fun Query():Cursor {
            var sqldb:SQLiteDatabase=this.writableDatabase
            var cursor=sqldb.rawQuery("SELECT * FROM " + dbTable,null)
          return cursor

        }fun Delete(selection:String,selectionArgs:Array<String>):Int{
        var sqldb:SQLiteDatabase=this.writableDatabase
        var count=sqldb.delete(dbTable,selection,selectionArgs)
        return count
        }
        fun Update(contentValues: ContentValues,selection: String,selectionArgs: Array<String>): Long {
            var sqldb:SQLiteDatabase=this.writableDatabase
            var updates=sqldb.update(dbTable,contentValues,selection,selectionArgs)
            return updates.toLong()
        }




}