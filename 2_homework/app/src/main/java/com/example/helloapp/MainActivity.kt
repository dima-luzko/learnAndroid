package com.example.helloapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private var mathFunction: Int = 0
    private var number1: Int = 0
    private var number2: Int = 0
    private var res: Int = 0
    private lateinit var resultText: TextView
    private lateinit var zeroButton: AppCompatButton
    private lateinit var oneButton: AppCompatButton
    private lateinit var twoButton: AppCompatButton
    private lateinit var threeButton: AppCompatButton
    private lateinit var fourButton: AppCompatButton
    private lateinit var fiveButton: AppCompatButton
    private lateinit var sixButton: AppCompatButton
    private lateinit var sevenButton: AppCompatButton
    private lateinit var eightButton: AppCompatButton
    private lateinit var nineButton: AppCompatButton
    private lateinit var commaButton: AppCompatButton
    private lateinit var plusButton: MaterialButton
    private lateinit var minusButton: MaterialButton
    private lateinit var multiplyButton: MaterialButton
    private lateinit var devideButton: MaterialButton
    private lateinit var equalsButton: MaterialButton
    private lateinit var squareRootOfYButton: MaterialButton
    private lateinit var squareRootOf2Button: MaterialButton
    private lateinit var powerToYButton: MaterialButton
    private lateinit var powerTo2Button: MaterialButton
    private lateinit var fractionButton: MaterialButton
    private lateinit var cleanButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun handleClicks() {
        resultText = findViewById(R.id.resultText)
        zeroButton = findViewById(R.id.zeroButton)
        oneButton = findViewById(R.id.oneButton)
        twoButton = findViewById(R.id.twoButton)
        threeButton = findViewById(R.id.threeButton)
        fourButton = findViewById(R.id.fourButton)
        fiveButton = findViewById(R.id.fiveButton)
        sixButton = findViewById(R.id.sixButton)
        sevenButton = findViewById(R.id.sevenButton)
        eightButton = findViewById(R.id.eightButton)
        nineButton = findViewById(R.id.nineButton)
        commaButton = findViewById(R.id.commaButton)
        plusButton = findViewById(R.id.plusButton)
        minusButton = findViewById(R.id.minusButton)
        multiplyButton = findViewById(R.id.multiplyButton)
        devideButton = findViewById(R.id.devideButton)
        equalsButton = findViewById(R.id.equalsButton)
        squareRootOfYButton = findViewById(R.id.squareRootOfYButton)
        squareRootOf2Button = findViewById(R.id.squareRootOf2Button)
        powerToYButton = findViewById(R.id.powerToYButton)
        powerTo2Button = findViewById(R.id.powerTo2Button)
        fractionButton = findViewById(R.id.fractionButton)
        cleanButton = findViewById(R.id.cleanButton)

        zeroButton.setOnClickListener {
            setText(getString(R.string.zero))
        }
        oneButton.setOnClickListener {
            setText(getString(R.string.one))

        }
        twoButton.setOnClickListener {
            setText(getString(R.string.two))

        }
        threeButton.setOnClickListener {
            setText(getString(R.string.three))
        }
        fourButton.setOnClickListener {
            setText(getString(R.string.four))
        }
        fiveButton.setOnClickListener {
            setText(getString(R.string.five))
        }
        sixButton.setOnClickListener {
            setText(getString(R.string.six))
        }
        sevenButton.setOnClickListener {
            setText(getString(R.string.seven))
        }
        eightButton.setOnClickListener {
            setText(getString(R.string.eight))
        }
        nineButton.setOnClickListener {
            setText(getString(R.string.nine))
        }
        commaButton.setOnClickListener {
            setText(getString(R.string.comma))
        }
        plusButton.setOnClickListener {
            number1 = resultText.text.toString().toInt()
            resultText.text = ""
            mathFunction = setText(getString(R.string.plus)).toString().toInt()
        }
        minusButton.setOnClickListener {
            setText(getString(R.string.minus))
        }
        multiplyButton.setOnClickListener {
            check(R.id.multiplyButton)
        }
        devideButton.setOnClickListener {

        }
        equalsButton.setOnClickListener {
            res = number1 + number2
            setText("$number1$mathFunction$number2")
        }
        squareRootOfYButton.setOnClickListener {

        }
        squareRootOf2Button.setOnClickListener {

        }
        powerToYButton.setOnClickListener {

        }
        powerTo2Button.setOnClickListener {

        }
        fractionButton.setOnClickListener {

        }
        cleanButton.setOnClickListener {
            resultText = findViewById(R.id.resultText)
            val str = resultText.text.toString()
            if (str.isNotEmpty()) resultText.text = str.substring(0, str.length - 1)
        }
        cleanButton.setOnLongClickListener {
            resultText = findViewById(R.id.resultText)
            resultText.text = getString(R.string.zero)
            true
        }
    }

    private fun check(v: Int) {
        resultText = findViewById(R.id.resultText)
        when (v) {
            R.id.plusButton -> {
                res = number1 + number2
            }
            R.id.oneButton -> {
                 number1 = getString(R.string.one).toInt()
                resultText.text = number1.toString()
            }
            R.id.twoButton -> {
                number2 = getString(R.string.two).toInt()
                resultText.text = number2.toString()
            }
            R.id.threeButton -> {
//                number2 = getString(R.string.two).toInt()
//                resultText.text = number2.toString()
            }
            R.id.equalsButton -> {
                resultText.text = res.toString()
            }
            R.id.multiplyButton -> {
                res = number1 * number2
            }
        }
    }

    private fun setText(str: String) {

        resultText = findViewById(R.id.resultText)
        resultText.append(str)

    }

    override fun onStart() {
        super.onStart()
        handleClicks()
    }

    override fun onStop() {
        super.onStop()
        oneButton.setOnClickListener(null)
        twoButton.setOnClickListener(null)
        plusButton.setOnClickListener(null)
        equalsButton.setOnClickListener(null)

    }


}