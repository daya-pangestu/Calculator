package com.daya.calculator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daya.calculator.view.calculator.CalculatorFragment
import com.daya.calculator.view.history.HistoryCalcFragment
class ScreenSliderAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int =2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0-> CalculatorFragment()
            1-> HistoryCalcFragment()
            else->throw IllegalStateException("fragment not found")
        }

}