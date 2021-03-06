package com.daya.calculator

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.daya.calculator.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overlapStatusBar()

        binding.vp2.apply {
            adapter = ScreenSliderAdapter(this@MainActivity)
        }
        TabLayoutMediator(binding.tab, binding.vp2) { tab, position ->
            tab.icon = when (position) {
                0 -> ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.ic_baseline_calculate_24
                )
                1 -> ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.ic_baseline_history_toggle_off_24
                )
                else -> throw IllegalStateException("cannot find icon position")
            }
        }.attach()
    }

    private fun overlapStatusBar() {
        if (Build.VERSION.SDK_INT >= 30) {
            ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, windowInsets ->

                val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
                view.layoutParams = (view.layoutParams as FrameLayout.LayoutParams).apply {
                    leftMargin = insets.left
                    bottomMargin = insets.bottom
                    rightMargin = insets.right
                }
                WindowInsetsCompat.CONSUMED
            }
        } else {
            ViewCompat.setOnApplyWindowInsetsListener(
                window.decorView
            ) { v, insets ->
                v.setPadding(0, 0, 0, v.paddingBottom)
                insets
            }
        }
    }
}