package com.example.quiz

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

interface FragmentInterface {
    fun startQuestions()
    fun finishQuestions()
    fun answerQuestion(position: Int, result: Boolean)
}

class MainActivity : AppCompatActivity(), FragmentInterface {

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
        replaceFragment(QuestionFragment())
    }

    override fun finishQuestions() {
        replaceFragment(ResultFragment())
    }

    override fun answerQuestion(position: Int, result: Boolean) {
        TODO("Not yet implemented")
    }

}