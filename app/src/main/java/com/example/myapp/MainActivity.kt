package com.example.myapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.R
import com.example.myapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    // ViewModel-ді инициализациялау [cite: 52]
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val amountInput = findViewById<EditText>(R.id.amountInput)
        val discountInput = findViewById<EditText>(R.id.discountInput)
        val calcButton = findViewById<Button>(R.id.calcButton)
        val resultText = findViewById<TextView>(R.id.resultText)
        val errorText = findViewById<TextView>(R.id.errorText)

        // Күйді бақылау және экранды жаңарту [cite: 53]
        viewModel.state.observe(this) { state ->
            resultText.text = state.resultText
            errorText.text = state.errorText
        }

        // Батырманы басқанда деректерді ViewModel-ге жіберу [cite: 54]
        calcButton.setOnClickListener {
            viewModel.calculate(
                amountInput.text.toString(),
                discountInput.text.toString()
            )
        }
    }
}