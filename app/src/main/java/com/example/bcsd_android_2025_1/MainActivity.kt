package com.example.bcsd_android_2025_1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Button
import android.widget.TextView
import android.app.Activity
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var numberTextView: TextView

    private val randomNumberLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newCount = result.data?.getIntExtra("randomNumber", count)
            if (newCount != null) {
                count = newCount
                numberTextView.text = count.toString()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        numberTextView = findViewById(R.id.textview_numberview)
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(this, R.color.darkblue))
        )
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toastButton: Button = findViewById<Button>(R.id.button_toast)
        val countButton: Button = findViewById<Button>(R.id.button_count)
        val randomButton: Button = findViewById(R.id.button_random)

        toastButton.setOnClickListener {
            Toast.makeText(this, "Toast 버튼 클릭", Toast.LENGTH_SHORT).show()
        }

        countButton.setOnClickListener {
            count++
            numberTextView.text = count.toString()
        }

        randomButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("count", count) // 현재 count 값 전달
            randomNumberLauncher.launch(intent)
        }


    }



}