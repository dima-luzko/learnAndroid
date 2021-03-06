package com.example.quiz

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.system.exitProcess

class HelloFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hello, container, false)
    }

    override fun onStart() {
        super.onStart()
        view?.findViewById<View>(R.id.noButton)?.setOnClickListener {
            exitProcess(0)
        }
        view?.findViewById<View>(R.id.yesButton)?.setOnClickListener {
            (requireActivity() as FragmentInterface).startQuestions()
        }
    }

    override fun onStop() {
        super.onStop()

    }

    companion object {
        @JvmStatic
        fun newInstance() = HelloFragment()
    }
}