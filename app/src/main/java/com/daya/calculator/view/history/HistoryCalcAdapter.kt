package com.daya.calculator.view.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daya.calculator.data.db.HistoryCalcModel
import com.daya.calculator.databinding.ItemHistoryCalcBinding

//ListAdapter is extended from recyclerview
class HistoryCalcAdapter(
    private val onItemClick : (Double, Double) -> Unit
) : ListAdapter<HistoryCalcModel, HistoryCalcAdapter.HistoryCalcViewHolder>(diffHistoryCalc) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryCalcViewHolder {
        val binding =
            ItemHistoryCalcBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryCalcViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: HistoryCalcViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }
    inner class HistoryCalcViewHolder(
        private val binding: ItemHistoryCalcBinding,
        private val onItemClick: (Double, Double) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(history: HistoryCalcModel) {
            binding.formulaResultCalc.text = "${history.formula}=${history.result}"
        }
    }

    companion object {
        val diffHistoryCalc = object : DiffUtil.ItemCallback<HistoryCalcModel>(){
            override fun areItemsTheSame(
                oldItem: HistoryCalcModel,
                newItem: HistoryCalcModel
            ): Boolean= oldItem.id == newItem.id
            override fun areContentsTheSame(
                oldItem: HistoryCalcModel,
                newItem: HistoryCalcModel
            ): Boolean = oldItem == newItem

        }
    }
}