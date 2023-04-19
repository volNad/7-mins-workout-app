
package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityCalculatorBinding
import java.math.BigDecimal
import java.math.RoundingMode

class CalculatorActivity : AppCompatActivity() {

    private var binding: ActivityCalculatorBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarCalculatorActivity)
        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate BMI"
        }

        binding?.toolbarCalculatorActivity?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding?.btnCalculateBMI?.setOnClickListener {
            if (validateMetricUnit()) {
                val height : Float = binding?.etHeight?.text.toString().toFloat() / 100
                val weight : Float = binding?.etWeight?.text.toString().toFloat()
                val bmi = weight / (height*height)
                displayBmiResults(bmi)

            } else {
                Toast.makeText(this@CalculatorActivity,
                    "Please enter valid values",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    override fun onBackPressed() {
        finish()

    }

    private fun displayBmiResults (bmi: Float) {

        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very underweight"
            bmiDescription = "Oops! You are really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You are really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You are really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You are really need to take care of yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Obese"
            bmiDescription = "Oops! You are really need to take care of yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Severely Obese"
            bmiDescription = "Oops! You are in a very dangerous condition! Act now!"
        } else  {
            bmiLabel = "Very Severely Obese"
            bmiDescription = "Oops! You are in a very dangerous condition! Act now!"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvCalculateAdvice?.text = bmiDescription
        binding?.tvCalculateConclusion?.text = bmiLabel
        binding?.tvResultBMI?.text = bmiValue
    }

   private fun validateMetricUnit(): Boolean {
       var isValid = true
       if(binding?.etWeight?.text.toString().isEmpty() ||
           binding?.etHeight?.text.toString().isEmpty() ) {
           isValid = false
       }

       return isValid
   }

}