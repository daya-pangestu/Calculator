package com.daya.calculator.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryCalcModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var formula: String,
    var result:String,
)