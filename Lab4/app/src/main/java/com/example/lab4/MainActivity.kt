package com.example.lab4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        result.data?.let { intent ->
            if (result.resultCode == Activity.RESULT_OK) {
                findViewById<TextView>(R.id.tvMeal).text = buildString {
                    append("飲料：").append(intent.getStringExtra("drink")).append("\n\n")
                    append("甜度：").append(intent.getStringExtra("sugar")).append("\n\n")
                    append("冰塊：").append(intent.getStringExtra("ice"))
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnChoice).setOnClickListener {
            startForResult.launch(Intent(this, SecActivity::class.java))
        }
    }
}
