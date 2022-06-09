package com.clean.example.utils.extensions

import android.app.Activity
import androidx.appcompat.app.AlertDialog

interface Callbackable {
    fun ok()
    fun cancel()
}

fun dialogMessage(title: String?, text: String?, activity: Activity, positiveText: String = "Ok") {
    activity.runOnUiThread {
        val dlgAlert = AlertDialog.Builder(activity)
        dlgAlert.setMessage(text)
        dlgAlert.setTitle(title)
        dlgAlert.setCancelable(true)
        dlgAlert.setPositiveButton(positiveText) { dialog, which -> }
        dlgAlert.create().show()
    }
}

fun dialogConfirm(
    title: String?, text: String?, activity: Activity, callback: Callbackable,
    positiveText: String = "Ok", negativeText: String = "Отмена"
) {
    activity.runOnUiThread {
        val dlgAlert = AlertDialog.Builder(activity)
        dlgAlert.setMessage(text)
        dlgAlert.setTitle(title)
        dlgAlert.setCancelable(true)
        dlgAlert.setPositiveButton(positiveText) { dialog, which -> callback.ok() }
        dlgAlert.setNegativeButton(negativeText) { dialog, which -> callback.cancel() }
        dlgAlert.create().show()
    }
}