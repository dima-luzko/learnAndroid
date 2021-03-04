package com.example.helloapp

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var view: LinearLayout
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
            setText(getString(R.string.plus))
        }
        minusButton.setOnClickListener {
            setText(getString(R.string.minus))
        }
        multiplyButton.setOnClickListener {

        }
        devideButton.setOnClickListener {

        }
        equalsButton.setOnClickListener {
            setText(getString(R.string.equals))
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

        }
    }

    private fun check(v: View?) {
        resultText = findViewById(R.id.resultText)
        when (v?.id) {
            R.id.plusButton -> {
                resultText.text = getString(R.string.plus)
            }
            R.id.oneButton -> {
                resultText.text = getString(R.string.one)
            }
            R.id.twoButton -> {
                resultText.text = getString(R.string.two)
            }
            R.id.equalsButton -> {
                resultText.text = getString(R.string.equals)
            }
        }
    }

    private fun setText(str: String) {
        resultText = findViewById(R.id.resultText)
        resultText.text = str
    }

    override fun onStart() {
        super.onStart()
        handleClicks()
    }

}