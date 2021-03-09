package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.button.MaterialButton

private const val RESULT_SUM = "RESULT_SUM"

class ResultFragment : Fragment() {

    private var resultSum: Int = 0
    private lateinit var tryAgainButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            resultSum = it.getInt(RESULT_SUM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.numberOfPoints).text = resultSum.toString()
    }

    override fun onStart() {
        super.onStart()
        tryAgainButton = view!!.findViewById(R.id.tryAgainButton)
    }

    companion object {
        @JvmStatic
        fun newInstance(resultSum: Int) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putInt(RESULT_SUM, resultSum)
                }
            }
    }
}