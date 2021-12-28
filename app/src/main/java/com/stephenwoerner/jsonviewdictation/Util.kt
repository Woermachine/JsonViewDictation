package com.stephenwoerner.jsonviewdictation

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

object Util {

    fun show(v : View?, show: Boolean?) {
        show ?: return

        v?.visibility = if(show) View.VISIBLE else View.GONE
    }

    fun setColor(v: View, colorRef : String?) {
        colorRef ?: return
        val colorInt = Color.parseColor(colorRef)
        when (v) {
            is TextView -> {
                v.setBackgroundColor(colorInt)
            }
            is ImageView -> {
                v.setBackgroundColor(colorInt)
            }
            is ConstraintLayout -> {
                v.setBackgroundColor(colorInt)
            }
        }
    }

    fun setTextColor(v: View, colorRef : String?) {
        colorRef ?: return
        val colorInt = Color.parseColor(colorRef)
        when (v) {
            is TextView -> {
                v.setTextColor(colorInt)
            }
            is Button -> {
                v.setTextColor(colorInt)
            }
        }
    }


}
