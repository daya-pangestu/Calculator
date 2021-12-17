package com.daya.calculator.view.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.daya.calculator.R
import com.daya.calculator.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {

    private var _binding : FragmentCalculatorBinding? = null
    private val binding get() = _binding!!
    private val viewModel : CalculatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCalculatorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textDisplay.showSoftInputOnFocus = false
        val numPadApter = NumPadAdapter{ //onclick
            when (it) {
                'C' -> {
                    viewModel.deleteText(binding.textDisplay.hasFocus(),binding.textDisplay.selectionStart,binding.textDisplay.selectionEnd)
                }
                '='-> viewModel.calculate()
                else ->{
                    val edtIsFocused = binding.textDisplay.isFocused
                    viewModel.input(it.toString(),if (edtIsFocused) binding.textDisplay.selectionEnd else null )
                }
            }
        }
        val numPadLayoutManager = GridLayoutManager(requireContext(),4)
        binding.rvNumpad.apply {
            layoutManager = numPadLayoutManager
            adapter =numPadApter
            setHasFixedSize(true)
        }

        viewModel.resultCalculationLiveData.observe(viewLifecycleOwner){
            val text = it.first
            val selectionPosition = it.second
            binding.textDisplay.setText(text)
            if (selectionPosition != null && text.isEmpty()) {
                binding.textDisplay.setSelection(selectionPosition)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}