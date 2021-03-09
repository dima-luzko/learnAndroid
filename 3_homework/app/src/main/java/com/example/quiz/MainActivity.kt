package com.example.quiz

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

interface FragmentInterface {
    fun startQuestions()
    fun finishQuestions()
    fun answerQuestion(answer: Answer)
}

enum class Answer {
    YES,
    NO
}

class MainActivity : AppCompatActivity(), FragmentInterface {

    private var currentQuestionPosition = 0
    private var numberCorrectPoint = 0

    private val answerArray: ArrayList<Answer> = arrayListOf(
        Answer.NO,
        Answer.NO,
        Answer.YES,
        Answer.NO,
        Answer.YES,
        Answer.YES,
        Answer.NO,
        Answer.YES,
        Answer.YES,
        Answer.YES
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        initFragment()
    }

    private fun initFragment() {
        replaceFragment(HelloFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun startQuestions() {
        replaceFragment(QuestionFragment.newInstance(currentQuestionPosition))
    }

    override fun finishQuestions() {
        replaceFragment(ResultFragment.newInstance(numberCorrectPoint))
    }

    override fun answerQuestion(answer: Answer) {
        if (answer == answerArray[currentQuestionPosition]) numberCorrectPoint++
        replaceFragment(QuestionFragment.newInstance(++currentQuestionPosition))
    }

}