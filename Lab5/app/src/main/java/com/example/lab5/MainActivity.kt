package com.example.lab5

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false) // 設定全屏顯示
        setContentView(R.layout.activity_main)

        setupWindowInsets()   // 設置 WindowInsets
        setupViewPager()      // 設置 ViewPager2
        logLifecycleEvent("onCreate")
    }

    private fun setupWindowInsets() {
        // 設定 View 的系統邊距
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupViewPager() {
        findViewById<ViewPager2>(R.id.viewPager2).apply {
            adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
            offscreenPageLimit = 1
        }
    }

    // 將生命週期的 Log 記錄抽取到單一函數中，方便維護
    private fun logLifecycleEvent(event: String) {
        Log.d("MainActivity", event)
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycleEvent("onRestart")
    }

    override fun onStart() {
        super.onStart()
        logLifecycleEvent("onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycleEvent("onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycleEvent("onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycleEvent("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycleEvent("onDestroy")
    }
}
