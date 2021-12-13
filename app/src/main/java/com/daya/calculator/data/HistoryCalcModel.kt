package com.daya.calculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryCalcModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var calculation: String,
    var result:String,
    var time: Long,
    var latitude : Double,
    var longitude : Double,
    var address : String
)