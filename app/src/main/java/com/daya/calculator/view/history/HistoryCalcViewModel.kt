package com.daya.calculator.view.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.daya.calculator.data.db.HistoryCalcDatabase

class HistoryCalcViewModel(application: Application) : AndroidViewModel(application) {

    private val historyDao = HistoryCalcDatabase.getInstance(application)?.historyCalcDao()!!

    val loadData = liveData {
        emitSource(historyDao.getAllHistoryCalc())

    }

}