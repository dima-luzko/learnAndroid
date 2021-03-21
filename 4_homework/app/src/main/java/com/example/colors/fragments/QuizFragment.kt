package com.example.colors.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.colors.R
import java.util.*
import kotlin.random.Random
import kotlin.system.exitProcess


class QuizFragment : Fragment() {

    private lateinit var backgroundColor: ConstraintLayout
    private lateinit var leftButton: TextView
    private lateinit var scores: TextView
    private lateinit var rightButton: TextView
    private lateinit var forgetButton: AppCompatButton
    private var currentColor: Int = 0
    private var numberOfPoints: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backgroundColor = view.findViewById(R.id.quiz)
        rightButton = view.findViewById(R.id.right_hash_code_button)
        leftButton = view.findViewById(R.id.left_hash_code_button)
        scores = view.findViewById(R.id.scores)
        forgetButton = view.findViewById(R.id.forget_button)

        nextQuestion()
    }

    override fun onStart() {
        super.onStart()
        forgetButton.setOnClickListener {
            exitProcess(0)
        }
    }

    override fun onStop() {
        super.onStop()
        forgetButton.setOnClickListener(null)
        leftButton.setOnClickListener(null)
        rightButton.setOnClickListener(null)
    }

    private fun nextQuestion() {
        currentColor = generateRandomColor()
        backgroundColor.setBackgroundColor(currentColor)

        when (generateRandomAnswerPosition()) {
            0 -> {
                initButtonsClickListeners(correctButton = leftButton, incorrectButton = rightButton)
            }
            1 -> {
                initButtonsClickListeners(correctButton = rightButton, incorrectButton = leftButton)
            }
        }
    }

    private fun initButtonsClickListeners(correctButton: TextView, incorrectButton: TextView) {
        correctButton.text = transformHexCodeToString(currentColor)
        correctButton.setOnClickListener {
            checkAnswer(currentColor)
        }

        incorrectButton.text = transformHexCodeToString(generateRandomColor())
        incorrectButton.setOnClickListener {
            checkAnswer(null)
        }
    }

    private fun checkAnswer(color: Int?) {
        if (currentColor == color) {
            numberOfPoints++
        } else {
            numberOfPoints--
        }
        scores.text = numberOfPoints.toString()
        nextQuestion()
    }

    private fun generateRandomColor(): Int {
        val rnd = Random
        return Color.rgb(
            rnd.nextInt(256),
            rnd.nextInt(256),
            rnd.nextInt(256)
        )
    }

    private fun generateRandomAnswerPosition() = Random.nextInt(2)

    private fun transformHexCodeToString(colorCode: Int) = Integer.toHexString(colorCode)
        .let { hexString ->
            getString(R.string.sharp) + hexString.substring(2).toUpperCase(Locale.getDefault())
        }

    companion object {
        @JvmStatic
        fun newInstance() = QuizFragment()
    }
}