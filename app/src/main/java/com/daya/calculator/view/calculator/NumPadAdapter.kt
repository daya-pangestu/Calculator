package com.daya.calculator.view.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.RecyclerView
import com.daya.calculator.R
import com.daya.calculator.data.NumPad
import com.daya.calculator.data.prederTeminateNumpad
import com.daya.calculator.databinding.ItemCalcNumberBinding

class NumPadAdapter(
    private val listKeyPad : List<NumPad> = prederTeminateNumpad,
    val onItemClick : (Char) -> Unit
) : RecyclerView.Adapter<NumPadAdapter.NumPadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumPadViewHolder {
        val binding =
            ItemCalcNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder =  NumPadViewHolder(binding)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            onItemClick(listKeyPad[position].char)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: NumPadViewHolder, position: Int) {
        val item = listKeyPad[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int = listKeyPad.size

    inner class NumPadViewHolder(private val binding: ItemCalcNumberBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(numPad: NumPad) {
            val textPad = numPad.char.toString()
                binding.pad.text = textPad
            if (!textPad.isDigitsOnly()) {
                binding.pad.setTextColor(ContextCompat.getColor(itemView.context, R.color.design_default_color_secondary))
            }
        }
    }
}