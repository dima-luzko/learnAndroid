package com.example.colors.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.colors.R
import java.util.*
import kotlin.random.Random


class QuizFragment : Fragment() {

    var numbersOfPoint: Int = 0
    private lateinit var backgroundColor: ConstraintLayout
    private lateinit var leftButton: TextView
    private lateinit var scores: TextView
    private lateinit var rightButton: TextView

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

        val (color, hexText) = getRandomColorAndConvertRandomColorToHexString()

        backgroundColor.setBackgroundColor(color)

        when (generateRandomPosition()) {
            0 -> {
                leftButton.text = hexText
                rightButton.text = randomHexCode()
            }
            1 -> {
                rightButton.text = hexText
                leftButton.text = randomHexCode()
            }
        }

        leftButton.setOnClickListener {
            backgroundColor.setBackgroundColor(generateRandomColor())
            numbersOfPoint += 1
            scores.text = numbersOfPoint.toString()
        }
        rightButton.setOnClickListener {
            numbersOfPoint -= 1
            scores.text = numbersOfPoint.toString()
        }
    }

    private fun generateRandomColor(): Int {
        val rnd = Random
        return Color.rgb(
            rnd.nextInt(256),
            rnd.nextInt(256),
            rnd.nextInt(256)
        )
    }

    private fun generateRandomPosition(): Int {
        val rnd = Random
        return rnd.nextInt(2)
    }



    private fun randomHexCode(): String {
        val hex = Integer.toHexString(generateRandomColor())
        return correctHexCode(hex)
    }

    private fun getRandomColorAndConvertRandomColorToHexString(): Pair<Int, String> {
        val randomColor = generateRandomColor()
        val hex = Integer.toHexString(randomColor)
        return Pair(randomColor, correctHexCode(hex))
    }

    private fun correctHexCode(hexCode: String): String {
        return getString(R.string.sharp) + hexCode.substring(2).toUpperCase(Locale.getDefault())
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuizFragment()
    }
}