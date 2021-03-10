package com.example.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

private const val POSITION = "POSITION"

class QuestionFragment : Fragment() {

    private var position: Int = 0
    private lateinit var noButton: MaterialButton
    private lateinit var yesButton: MaterialButton
    private lateinit var congratulations: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_question, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(resources.obtainTypedArray(R.array.images)) {
            view.findViewById<ImageView>(R.id.imageQuestionFragment)
                .setImageResource(this.getResourceId(position, 0))
            recycle()
        }
        val textArray = resources.getStringArray(R.array.questions)
        view.findViewById<TextView>(R.id.textQuestionFragment).text = textArray.getOrNull(position)
    }

    private fun getEmojiByUnicode(unicode: Int): String {
        return String(Character.toChars(unicode))
    }

    override fun onStart() {
        super.onStart()
        val textArray = resources.getStringArray(R.array.questions)
        congratulations = view!!.findViewById(R.id.textQuestionFragment)
        noButton = view!!.findViewById(R.id.noButtonQuestionFragment)
        yesButton = view!!.findViewById(R.id.yesButtonQuestionFragment)

        yesButton.setOnClickListener {
            (requireActivity() as FragmentInterface).answerQuestion(Answer.YES)
        }
        noButton.setOnClickListener {
            (requireActivity() as FragmentInterface).answerQuestion(Answer.NO)
        }

        if (position == textArray.size) {
            congratulations.text = getString(R.string.congratulation, getEmojiByUnicode(0x1F609))
            noButton.visibility = View.INVISIBLE
            yesButton.text = getString(R.string.get_results)
            yesButton.textSize = 20F
            yesButton.setOnClickListener {
                (requireActivity() as FragmentInterface).finishQuestions()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(position: Int) =
            QuestionFragment().apply {
                arguments = Bundle().apply {
                    putInt(POSITION, position)
                }
            }
    }
}
