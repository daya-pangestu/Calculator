package com.daya.calculator.view.calculator

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.notkamui.keval.keval

class CalculatorViewModel : ViewModel() {

    private val builder = StringBuilder()

    /**
    * string param = the text, formula, result
     * int param = selection position if exist, use null otherwise
    * */
    private val _resultCalculationLiveData = MutableLiveData<Pair<String,Int?>>(Pair("", null))
    val resultCalculationLiveData get() =  _resultCalculationLiveData

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
            _resultCalculationLiveData.value = wrapResult(builder.toString())

            //save to db

        }catch (e :Exception){
            Log.e("calculate number","${e.message}")
            builder.clear()
            //show error
            _resultCalculationLiveData.value = wrapResult("error")
        }
    }

    fun deleteText(hasFocus :Boolean = false,selectionStart:Int = 0,selectionEnd : Int = 0){
        try {
            if (!hasFocus) {
                val deletePosition = builder.length - 1
                builder.deleteCharAt(if (deletePosition > 0) deletePosition else 0)
                //show result after some number got deleted
                _resultCalculationLiveData.value = wrapResult( builder.toString())

            } else {
                builder.delete(if (selectionStart > 0) selectionStart - 1 else 0, selectionEnd)
                //show result with correct focus position
                _resultCalculationLiveData.value = wrapResult(builder.toString(),if (selectionEnd - 1 > 0) selectionEnd - 1 else 0)

            }
        } catch (e: IndexOutOfBoundsException) {//when you try to delete but no number on display
            //do nothing
            Log.e("calculatorControlleViewModel","${e.message}")
        }
    }

    fun input(text: String,selectionEnd: Int? = null) {
        if (selectionEnd == null){
            builder.append(text)
            _resultCalculationLiveData.value = wrapResult(builder.toString(),text.length)
            return
        }
        builder.insert(selectionEnd,text)
        //show calculated result
        _resultCalculationLiveData.value = wrapResult(builder.toString(), if (selectionEnd > 0) selectionEnd + 1 else text.length)
    }

    private fun wrapResult(text: String, selectionPosition: Int? = null) = Pair(text,selectionPosition)


}