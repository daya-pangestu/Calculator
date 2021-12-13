package com.daya.calculator.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.daya.calculator.databinding.FragmentHistoryCalcBinding

class HistoryCalcFragment : Fragment(){
    private var _binding: FragmentHistoryCalcBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryCalcViewModel by viewModels()
    private lateinit var historyCalcAdapter: HistoryCalcAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryCalcBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyCalcAdapter = HistoryCalcAdapter { lat, long ->

        }
        binding.rvHistoryCalc.apply {
            adapter = historyCalcAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}