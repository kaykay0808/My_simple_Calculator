package com.example.mysimplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.mysimplecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var lastNumeric:Boolean = false
    var lastDot:Boolean = false

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    // instead of using an onClickListener.
    fun onDigit(view: View){
        //Toast.makeText(this, "Button works", Toast.LENGTH_SHORT).show()
        //set text below from button
        binding.tvInput.append((view as Button).text) //<-- this makes the texview input show the text of the button.
        lastNumeric = true

        /*if(binding.tvInput.text.contains("1"))
            binding.tvInput.text = "Haha"*/

        
    }
    //clear function
    fun onClear(view: View){
        binding.tvInput.text =""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View){
        // We just want to run the if Statement if the number before was a number and was not a before that dot
        if(lastNumeric && !lastDot){
            binding.tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(binding.tvInput.text.toString())){
            binding.tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    // check if the operator is used
    private fun isOperatorAdded(value: String) : Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}