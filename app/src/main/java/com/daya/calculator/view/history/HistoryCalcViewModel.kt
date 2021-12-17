package com.daya.calculator.view.history

import android.annotation.SuppressLint
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daya.calculator.data.HistoryCalcModel
import com.notkamui.keval.keval
import kotlinx.coroutines.launch
import java.util.*

class HistoryCalcViewModel : ViewModel() {

    private val builder = StringBuilder()

    fun calculate() {
        val formula = builder.toString()

        try {
            val resultCalculate = formula.keval().toString()
            builder.clear()
            val resultNewFormat = if (resultCalculate.contains(".0")) {
                resultCalculate.replace(".0", "")
            } else resultCalculate
            builder.append(resultNewFormat)
            //show result


            //save to db

    }catch (e :Exception){
            Log.e("calculate number","${e.message}")
            builder.clear()
            //show error
        }
    }

    fun deleteText(hasFocus :Boolean = false,selectionStart:Int = 0,selectionEnd : Int = 0){
        try {
            if (!hasFocus) {
                val deletePosition = builder.length - 1
                builder.deleteCharAt(if (deletePosition > 0) deletePosition else 0)
                //show result after some number got deleted

            } else {
                builder.delete(if (selectionStart > 0) selectionStart - 1 else 0, selectionEnd)
                //show result with correct focus position

            }
        } catch (e: IndexOutOfBoundsException) {//when you try to delete but no number on display
            //do nothing
            Log.e("calculatorController","${e.message}")
        }
    }

    fun input(text: String,selectionEnd: Int = 0) {
        builder.insert(selectionEnd,text)
        //show calculated result
    }


}