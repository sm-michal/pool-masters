package com.example.poolmasters.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DB_NAME = "POOL_MASTERS_DB"

class DbHelper(val context: Context): SQLiteOpenHelper(context, DB_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("""
            create table players (
                id integer primary key,
                name text
            )            
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table players")
        onCreate(db)
    }

    fun removePlayers() {
        writableDatabase.execSQL("delete from players", emptyArray())
    }

    fun addPlayer(name: String) {
        writableDatabase.execSQL("insert into players (name) values (:1)", arrayOf(name))
    }

    fun getPlayers(): ArrayList<String> {
        with(readableDatabase.rawQuery("select name from players", emptyArray())) {
            moveToFirst()

            val result = arrayListOf<String>()
            while (!isAfterLast) {
                result.add(getString(0))

                moveToNext()
            }

            return result
        }
    }
}