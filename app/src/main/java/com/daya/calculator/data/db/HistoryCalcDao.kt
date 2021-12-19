package com.daya.calculator.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryCalcDao {

    @Query("SELECT * FROM historycalcmodel")
    fun getAllHistoryCalc(): LiveData<List<HistoryCalcModel>>

    @Query("DELETE FROM historycalcmodel")
    fun deleteAllHistory()

    @Insert
    fun addNewHistory(historyCalcModel: HistoryCalcModel)
}