package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calculateBmiButton = findViewById<Button>(R.id.btnCalculate)

        calculateBmiButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() * 0.01) * (height.toFloat() * 0.01))
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResults(bmi2Digits)
            }

            weightText.text.clear()
            heightText.text.clear()
        }
    }

    private fun validateInput(weight: String?, height: String?): Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                 Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                return false;
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                return false;
            }
            else -> {
                return true;
            }
        }
    }

    private fun displayResults(bmi: Float) {
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        val resultMessage = if (bmi < 18.5) "Underweight" else if (bmi < 24.9) "Normal weight" else "Overweight"
        resultDescription.text = resultMessage
        info.text = "(Normal range is 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when {
            bmi < 18.5 -> {
                resultText = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99-> {
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99-> {
                resultText = "Overweight"
                color = R.color.over_weight
            }
            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this, color))
        resultDescription.text = resultText
    }
}