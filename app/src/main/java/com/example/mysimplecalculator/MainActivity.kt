package com.example.mysimplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.mysimplecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // instead of using an onClickListener.
    fun onDigit(view: View) {
        //Toast.makeText(this, "Button works", Toast.LENGTH_SHORT).show()
        //set text below from button
        binding.tvInput.append((view as Button).text) //<-- this makes the texview input show the text of the button.
        lastNumeric = true

        /*if(binding.tvInput.text.contains("1"))
            binding.tvInput.text = "Haha"*/


    }

    //clear function
    fun onClear(view: View) {
        binding.tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        // We just want to run the if Statement if the number before was a number and was not a before that dot
        if (lastNumeric && !lastDot) {
            binding.tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = binding.tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")) {
                    // need to split String so it understand what is right and left of the operator
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    // example: 99 - 1
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    // THe calculation -> we convert it to Double and then to String
                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())


                }else if (tvValue.contains("/")) {
                    // need to split String so it understand what is right and left of the operator
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    // example: 99 - 1
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    // THe calculation -> we convert it to Double and then to String
                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())


                }else if (tvValue.contains("+")) {
                    // need to split String so it understand what is right and left of the operator
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    // example: 99 - 1
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    // THe calculation -> we convert it to Double and then to String
                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())


                }else if (tvValue.contains("*")) {
                    // need to split String so it understand what is right and left of the operator
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    // example: 99 - 1
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    // THe calculation -> we convert it to Double and then to String
                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }
    // This function removes second index after .0
    fun removeZeroAfterDot(result: String) : String{
        var value = result
        if(result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(binding.tvInput.text.toString())) {
            binding.tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    // check if the operator is used
    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}