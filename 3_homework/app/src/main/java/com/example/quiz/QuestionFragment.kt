package com.example.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

private const val TEXT = "question"
private const val IMAGE = "image"

class QuestionFragment : Fragment() {

    private var questionTextArray: Array<Int> = arrayOf(
        R.string.question_1,
        R.string.question_2, R.string.question_3,
        R.string.question_4, R.string.question_5,
        R.string.question_6, R.string.question_7,
        R.string.question_8, R.string.question_9,
        R.string.question_10
    )


    private var questionImagesArray: Array<Int> = arrayOf(
        R.drawable.image_for_question_1,
        R.drawable.image_for_question_2, R.drawable.image_for_question_3,
        R.drawable.image_for_question_4, R.drawable.image_for_question_5,
        R.drawable.image_for_question_6, R.drawable.image_for_question_7,
        R.drawable.image_for_question_8, R.drawable.image_for_question_9,
        R.drawable.image_for_question_10
    )

    private var text: String? = null
    private var image: Int? = null
    private var position = 0
    private var currentIndex = 0
    private lateinit var questionImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString(TEXT)
            //image = it.getInt(IMAGE,questionImagesArray[0])
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_question, container, false)
        questionImage = root.findViewById(R.id.imageQuestionFragment)
        questionImage.setImageResource(questionImagesArray[0])
        root.findViewById<TextView>(R.id.textQuestionFragment).text = text
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(text: String, image: Int) =
            QuestionFragment().apply {
                arguments = Bundle().apply {
                    putString(TEXT, text)
//                    putInt(IMAGE,questionImagesArray[9])
                }
            }
    }

    override fun onStart() {
        super.onStart()

        if (currentIndex < questionTextArray.size-1) {
            currentIndex++
        view?.findViewById<MaterialButton>(R.id.yesButtonQuestionFragment)?.setOnClickListener {
            (requireActivity() as FragmentInterface).answerQuestion(questionTextArray[currentIndex], true)
        }
        view?.findViewById<MaterialButton>(R.id.noButtonQuestionFragment)?.setOnClickListener {
            (requireActivity() as FragmentInterface).answerQuestion(0, false)
        }
        }

//        when (position) {
//            0 -> {
//                view?.findViewById<MaterialButton>(R.id.yesButtonQuestionFragment)
//                    ?.setOnClickListener {
//                        (requireActivity() as FragmentInterface).answerQuestion(0, true)
//                    }
//                view?.findViewById<MaterialButton>(R.id.noButtonQuestionFragment)
//                    ?.setOnClickListener {
//                        (requireActivity() as FragmentInterface).answerQuestion(0, false)
//                    }
//            }
//            1 -> {
//                view?.findViewById<MaterialButton>(R.id.yesButtonQuestionFragment)
//                    ?.setOnClickListener {
//                        (requireActivity() as FragmentInterface).answerQuestion(1, true)
//                    }
//                view?.findViewById<MaterialButton>(R.id.noButtonQuestionFragment)
//                    ?.setOnClickListener {
//                        (requireActivity() as FragmentInterface).answerQuestion(1, false)
//                    }
//            }
//            2 -> {
//                view?.findViewById<MaterialButton>(R.id.yesButtonQuestionFragment)
//                    ?.setOnClickListener {
//                        (requireActivity() as FragmentInterface).answerQuestion(2, true)
//                    }
//                view?.findViewById<MaterialButton>(R.id.noButtonQuestionFragment)
//                    ?.setOnClickListener {
//                        (requireActivity() as FragmentInterface).answerQuestion(2, false)
//                    }
//            }
//        }
    }



}
