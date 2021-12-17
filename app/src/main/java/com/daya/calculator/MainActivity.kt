package com.daya.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.daya.calculator.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vp2.apply {
            adapter = ScreenSliderAdapter(this@MainActivity)
        }
        TabLayoutMediator(binding.tab,binding.vp2){tab, position ->
            tab.icon = when (position) {
                0-> ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_baseline_calculate_24)
                1-> ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_baseline_history_toggle_off_24)
                else -> throw IllegalStateException("cannot find icon position")
            }
        }.attach()
    }
}