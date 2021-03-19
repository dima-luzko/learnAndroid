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

    private lateinit var backgroundColor: ConstraintLayout
    private lateinit var leftButton: TextView
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
        leftButton = view.findViewById(R.id.left_hash_code_button)
        rightButton = view.findViewById(R.id.right_hash_code_button)

        val colorId = backgroundColor.background
        
        backgroundColor.setBackgroundColor(generateRandomColor())
        
        


        leftButton.setOnClickListener {
            backgroundColor.setBackgroundColor(generateRandomColor())
            leftButton.text = convertRandomIntToHexString()
        }
        rightButton.setOnClickListener {
            backgroundColor.setBackgroundColor(generateRandomColor())
            rightButton.text = convertRandomIntToHexString()

        }
//        rightButton.text = convertRandomIntToHexString()
//        leftButton.text = convertRandomIntToHexString()

    }

    private fun increaseScore(view: View) {

    }

    private fun decreaseScore(view: View) {

    }

    private fun generateRandomColor(): Int {
        val rnd = Random
        return Color.argb(
            255,
            rnd.nextInt(256),
            rnd.nextInt(256),
            rnd.nextInt(256)
        )
    }

    private fun convertRandomIntToHexString(): String {
        val hex = Integer.toHexString(generateRandomColor())
        return getString(R.string.sharp) + hex.toUpperCase(Locale.ROOT)
    }


    companion object {
        @JvmStatic
        fun newInstance() = QuizFragment()
    }
}