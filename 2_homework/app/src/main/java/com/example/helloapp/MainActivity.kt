package com.example.helloapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.button.MaterialButton
import java.math.RoundingMode
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private val PLUS = 1
    private val MINUS = 2
    private val MULTIPLY = 3
    private val DEVIDE = 4
    private val POWER_TO_2 = 5
    private val POWER_TO_Y = 6
    private val SQUARE_ROOT_TO_2 = 7
    private val SQUARE_ROOT_TO_Y = 8
    private val FRACTION = 9

    private var dotPressed: Boolean = true
    private var isFirstPressed: Boolean = true
    private var mathFunction: Int = 0
    private var number1: Double = 0.0
    private var number2: Double = 0.0

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
    private lateinit var dotButton: AppCompatButton

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
        dotButton = findViewById(R.id.dotButton)
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
        dotButton.setOnClickListener {
            dotPressed = true
            setText(getString(R.string.comma))
        }


        plusButton.setOnClickListener {
            isFirstPressed = true
            number1 = resultText.text.toString().toDouble()
            mathFunction = PLUS
            resultText.text = getString(R.string.reset)
        }
        minusButton.setOnClickListener {
            isFirstPressed = true
            number1 = resultText.text.toString().toDouble()
            mathFunction = MINUS
            resultText.text = getString(R.string.reset)
        }
        multiplyButton.setOnClickListener {
            isFirstPressed = true
            number1 = resultText.text.toString().toDouble()
            mathFunction = MULTIPLY
            resultText.text = getString(R.string.reset)
        }
        devideButton.setOnClickListener {
            isFirstPressed = true
            number1 = resultText.text.toString().toDouble()
            mathFunction = DEVIDE
            resultText.text = getString(R.string.reset)
        }
        equalsButton.setOnClickListener {
            if (isFirstPressed) {
                number2 = resultText.text.toString().toDouble()
                isFirstPressed = false
            }
            checkMathOperation()
        }
        squareRootOfYButton.setOnClickListener {
            isFirstPressed = true
            number1 = resultText.text.toString().toDouble()
            mathFunction = SQUARE_ROOT_TO_Y
            resultText.text = getString(R.string.reset)
        }
        squareRootOf2Button.setOnClickListener {
            isFirstPressed = true
            number1 = resultText.text.toString().toDouble()
            number1 = sqrt(number1)
            resultText.text = number1.toString()
           // resultText.text = number1.toBigDecimal().setScale(4, RoundingMode.HALF_EVEN).toDouble().toString()

        }
        powerToYButton.setOnClickListener {
            isFirstPressed = true
            number1 = resultText.text.toString().toDouble()
            mathFunction = POWER_TO_Y
            resultText.text = getString(R.string.reset)
        }
        powerTo2Button.setOnClickListener {
            isFirstPressed = true
            number1 = resultText.text.toString().toDouble()
            number1 = number1.pow(2)
            resultText.text = number1.toString()
        }
        fractionButton.setOnClickListener {
            isFirstPressed = true
            number1 = resultText.text.toString().toDouble()
            if(number1.toString() != getString(R.string.zero)) {
                number1 = 1/number1
                resultText.text = number1.toString()
            } else {
                resultText.text = getString(R.string.error)
            }
        }


        cleanButton.setOnClickListener {
            isFirstPressed = true
            resultText = findViewById(R.id.resultText)
            val str = resultText.text.toString()
            if (str.isNotEmpty()) resultText.text = str.substring(0, str.length - 1)
        }
        cleanButton.setOnLongClickListener {
            isFirstPressed = true
            resultText = findViewById(R.id.resultText)
            resultText.text = getString(R.string.zero)
            true
        }
    }

    private fun checkMathOperation() {
        when (mathFunction) {
            PLUS -> {
                    number1 += number2
                    resultText.text = number1.toString()
            }
            MINUS -> {
                number1 -= number2
                resultText.text = number1.toString()
            }
            MULTIPLY -> {
                number1 *= number2
                resultText.text = number1.toString()
            }
            DEVIDE -> {
                number1 /= number2
                resultText.text = number1.toString()
            }
            SQUARE_ROOT_TO_Y -> {
                number1 = number1.pow(1/number2)
                resultText.text = number1.toString()
            }
            POWER_TO_Y -> {
                number1 = number1.pow(number2)
                resultText.text = number1.toInt().toString()
            }
        }
    }

    private fun setText(str: String) {
        resultText = findViewById(R.id.resultText)
        if (resultText.text.toString() == getString(R.string.zero)) resultText.text =
            str else resultText.append(str)
    }

    override fun onStart() {
        super.onStart()
        handleClicks()
    }

    override fun onStop() {
        super.onStop()
        zeroButton.setOnClickListener(null)
        oneButton.setOnClickListener(null)
        twoButton.setOnClickListener(null)
        threeButton.setOnClickListener(null)
        fourButton.setOnClickListener(null)
        fiveButton.setOnClickListener(null)
        sixButton.setOnClickListener(null)
        sevenButton.setOnClickListener(null)
        eightButton.setOnClickListener(null)
        nineButton.setOnClickListener(null)
        dotButton.setOnClickListener(null)
        plusButton.setOnClickListener(null)
        minusButton.setOnClickListener(null)
        multiplyButton.setOnClickListener(null)
        devideButton.setOnClickListener(null)
        cleanButton.setOnClickListener(null)
        powerTo2Button.setOnClickListener(null)
        powerToYButton.setOnClickListener(null)
        squareRootOf2Button.setOnClickListener(null)
        squareRootOfYButton.setOnClickListener(null)
        fractionButton.setOnClickListener(null)
        equalsButton.setOnClickListener(null)
    }

}