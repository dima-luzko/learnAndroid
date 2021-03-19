package com.example.colors

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.colors.adapter.ColorsAdapter
import com.example.colors.data.Color

class ColorsListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_colors_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)

        with(recyclerView) {
            layoutManager = androidx.recyclerview.widget.GridLayoutManager(
                activity,
                3,
                androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                false
            )
            adapter = ColorsAdapter(colorsList()) {
                (requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager).apply {
                    setPrimaryClip(
                        ClipData.newPlainText(
                            getString(R.string.copy_color_hash_code),
                            it.colorHashCode
                        )
                    )
                }
                showDialog(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        colorsList()
    }

    private fun showDialog(color: Color) {
        val dialog = context?.let { Dialog(it) }

        with(dialog) {
            this?.setCancelable(false)
            this?.setContentView(R.layout.popup_window)
        }

        val colorBackground = dialog?.findViewById<View>(R.id.colors_dialog_form)
        val colorTextName = dialog?.findViewById<TextView>(R.id.colors_dialog_name)
        val colorCode = dialog?.findViewById<TextView>(R.id.colors_dialog_hash_code)
        val button = dialog?.findViewById<AppCompatButton>(R.id.messageButton)

        colorBackground?.setBackgroundColor(color.colorForm)
        colorTextName?.text = color.colorName
        colorCode?.text = color.colorHashCode
        button?.setOnClickListener {
            dialog?.dismiss()
        }
        dialog?.show()
    }


    private fun colorsList(): List<Color> {
        val colorForm = resources.getIntArray(R.array.colors_form)
        val colorsName = resources.getStringArray(R.array.colors_name)
        val colorsHashCode = resources.getStringArray(R.array.colors_hash_code)

        return colorForm.mapIndexed { index, colorForm ->
            Color(
                colorForm = colorForm,
                colorName = colorsName[index],
                colorHashCode = colorsHashCode[index]
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ColorsListFragment()
    }
}