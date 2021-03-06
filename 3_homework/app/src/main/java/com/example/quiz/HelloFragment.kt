package com.example.quiz

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
        view?.findViewById<View>(R.id.noButtonHelloFragment)?.setOnClickListener {
            exitProcess(0)
        }
        view?.findViewById<View>(R.id.yesButtonHelloFragment)?.setOnClickListener {
            (requireActivity() as FragmentInterface).startQuestions()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HelloFragment()
    }
}