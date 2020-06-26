package com.example.todoapplication.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.todoapplication.R
import kotlinx.android.synthetic.main.fragment_progress_dialog.view.*

class ProgressDialog(private val title: String) : DialogFragment() {

    fun showDialog(fragmentManager: FragmentManager) {
        show(fragmentManager, this@ProgressDialog::class.java.simpleName)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress_dialog, container, false).apply {
            tv_title_progress_dialog.text = title
            tv_message_progress_dialog.text =
                this@ProgressDialog.requireContext().resources.getString(R.string.please_wait)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ThemeOverlay_AppCompat_Dialog_Alert)
    }
}