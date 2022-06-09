package com.clean.example.utils.extensions

import android.app.Activity
import androidx.navigation.findNavController
import com.clean.example.R

fun Activity.mainNavController() = findNavController(R.id.main_screen_nav_host_fragment)

fun Activity.showError(message: String) {
    dialogMessage("Ошибка", message, this)
}