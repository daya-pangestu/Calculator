package com.daya.calculator.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        HistoryCalcModel::class
    ],

    version = 1
)
abstract class HistoryCalcDatabase : RoomDatabase() {
    abstract fun historyCalcDao(): HistoryCalcDao

    companion object{
        private var INSTANCE: HistoryCalcDatabase? = null

        fun getInstance(context: Context): HistoryCalcDatabase? {
            if (INSTANCE == null) {
                synchronized(HistoryCalcDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        HistoryCalcDatabase::class.java, "history.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}