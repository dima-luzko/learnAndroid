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

    private var questionImagesArray: Array<Int> = arrayOf(R.drawable.image_for_question_1,
        R.drawable.image_for_question_2, R.drawable.image_for_question_3,
        R.drawable.image_for_question_4, R.drawable.image_for_question_5,
        R.drawable.image_for_question_6, R.drawable.image_for_question_7,
        R.drawable.image_for_question_8, R.drawable.image_for_question_9,
        R.drawable.image_for_question_10)

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
        replaceFragment(QuestionFragment.newInstance(getString(R.string.question_1),questionImagesArray[0]))
    }

    override fun finishQuestions() {
        replaceFragment(ResultFragment())
    }

    override fun answerQuestion(position: Int, result: Boolean) {
        when(position){
            0 -> replaceFragment(QuestionFragment.newInstance(getString(R.string.question_2),questionImagesArray[1]))
            1 -> replaceFragment(QuestionFragment.newInstance(getString(R.string.question_3),questionImagesArray[2]))
            2 -> replaceFragment(QuestionFragment.newInstance(getString(R.string.question_4),questionImagesArray[3]))
            3 -> replaceFragment(QuestionFragment.newInstance(getString(R.string.question_5),questionImagesArray[4]))
            4 -> replaceFragment(QuestionFragment.newInstance(getString(R.string.question_6),questionImagesArray[5]))
            5 -> replaceFragment(QuestionFragment.newInstance(getString(R.string.question_7),questionImagesArray[6]))
            6 -> replaceFragment(QuestionFragment.newInstance(getString(R.string.question_8),questionImagesArray[7]))
            7 -> replaceFragment(QuestionFragment.newInstance(getString(R.string.question_9),questionImagesArray[8]))
            8 -> replaceFragment(QuestionFragment.newInstance(getString(R.string.question_10),questionImagesArray[9]))
        }
    }

}