package com.example.lab6

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val items = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupWindowInsets()    // 設定 WindowInsets 邊距
        setupButtonListeners() // 設定按鈕點擊事件
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupButtonListeners() {
        findViewById<Button>(R.id.btnToast).setOnClickListener { showToast("預設 Toast") }
        findViewById<Button>(R.id.btnSnackBar).setOnClickListener { showSnackbar(it) }
        findViewById<Button>(R.id.btnDialog1).setOnClickListener { showButtonDialog() }
        findViewById<Button>(R.id.btnDialog2).setOnClickListener { showListDialog() }
        findViewById<Button>(R.id.btnDialog3).setOnClickListener { showSingleChoiceDialog() }
    }

    private fun showSnackbar(view: android.view.View) {
        Snackbar.make(view, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
            .setAction("按鈕") { showToast("已回應") }
            .show()
    }

    private fun showButtonDialog() {
        AlertDialog.Builder(this)
            .setTitle("按鈕式 AlertDialog")
            .setMessage("AlertDialog 內容")
            .setNeutralButton("左按鈕") { _, _ -> showToast("左按鈕") }
            .setNegativeButton("中按鈕") { _, _ -> showToast("中按鈕") }
            .setPositiveButton("右按鈕") { _, _ -> showToast("右按鈕") }
            .show()
    }

    private fun showListDialog() {
        AlertDialog.Builder(this)
            .setTitle("列表式 AlertDialog")
            .setItems(items) { _, i -> showToast("你選的是${items[i]}") }
            .show()
    }

    private fun showSingleChoiceDialog() {
        var selectedPosition = 0
        AlertDialog.Builder(this)
            .setTitle("單選式 AlertDialog")
            .setSingleChoiceItems(items, selectedPosition) { _, i -> selectedPosition = i }
            .setPositiveButton("確定") { _, _ -> showToast("你選的是${items[selectedPosition]}") }
            .show()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
