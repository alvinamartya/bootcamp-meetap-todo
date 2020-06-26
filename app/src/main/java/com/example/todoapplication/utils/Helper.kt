package com.example.todoapplication.utils

import android.app.AlertDialog
import android.content.Context

fun showMessage(message: String, title: String, context: Context) {
    AlertDialog.Builder(context)
        .setMessage("$message!")
        .setTitle(title)
        .setCancelable(false)
        .setPositiveButton("OK") { _, _ ->
            // empty code
        }
        .show()
}