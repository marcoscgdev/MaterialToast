/*
 * MaterialToastSample - MaterialToast.java
 * Created by Marcos Calvo García on 21/12/18 18:26.
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 21/12/18 18:26.
 */
package com.marcoscg.materialtoast

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.ColorUtils

class MaterialToast(private val activity: Activity?) {

    private var message: String? = null
    private var icon: Drawable? = null
    private var duration = Toast.LENGTH_SHORT

    fun setMessage(message: String?): MaterialToast {
        this.message = message
        return this
    }

    fun setMessage(messageRes: Int): MaterialToast {
        setMessage(activity?.resources?.getString(messageRes))
        return this
    }

    fun setIcon(icon: Drawable?): MaterialToast {
        this.icon = icon
        return this
    }

    fun setIcon(iconRes: Int): MaterialToast {
        setIcon(activity?.resources?.getDrawable(iconRes))
        return this
    }

    fun setDuration(duration: Int): MaterialToast {
        this.duration = duration
        return this
    }

    fun setBackgroundColor(bgColor: Int): MaterialToast {
        Companion.bgColor = bgColor
        return this
    }

    fun show() {
        show(null)
    }

    fun show(view: View?) {
        val toast = getToast(activity, icon)

        toast?.let {
            if (view != null) {
                toast.showAsDropDown(view)
            } else if (activity?.window != null) {
                toast.showAtLocation(activity.window.decorView, Gravity.BOTTOM, 0, activity.resources.getDimensionPixelSize(R.dimen.mt_bottom_margin))
            }
        }

        Handler().postDelayed({ if (toast != null && activity != null && !activity.isFinishing) toast.dismiss() }, getRealDuration(duration).toLong())
    }

    private fun getToast(activity: Activity?, icon: Drawable?): PopupWindow? {
        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
                ?: return null

        val view = inflater.inflate(R.layout.mt_main_layout, null)
        val cardView: CardView = view.findViewById(R.id.card_view)
        cardView.setCardBackgroundColor(bgColor)
        val textView: AppCompatTextView = view.findViewById(android.R.id.message)
        textView.typeface = Typeface.create("sans-serif-condensed", Typeface.NORMAL)

        if (message != null) textView.text = message
        if (isColorDark(bgColor)) textView.setTextColor(Color.WHITE)

        val popupWindow = PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

        popupWindow.animationStyle = R.style.MaterialToastAnimation

        if (icon != null) {
            val iconIV: AppCompatImageView = view.findViewById(R.id.icon)
            iconIV.visibility = View.VISIBLE
            iconIV.setImageDrawable(icon)
        }

        return popupWindow
    }

    companion object {
        private var bgColor = Color.WHITE
        fun makeText(activity: Activity?, message: String?, icon: Drawable?, duration: Int): MaterialToast {
            return MaterialToast(activity)
                    .setMessage(message)
                    .setIcon(icon)
                    .setBackgroundColor(Color.WHITE)
                    .setDuration(duration)
        }

        fun makeText(activity: Activity?, message: String?, duration: Int): MaterialToast {
            return makeText(activity, message, null, duration)
        }

        fun makeText(activity: Activity, messageId: Int, duration: Int): MaterialToast {
            return makeText(activity, activity.resources.getString(messageId), null, duration)
        }

        fun makeText(activity: Activity, messageId: Int, icon: Drawable?, duration: Int): MaterialToast {
            return makeText(activity, activity.resources.getString(messageId), icon, duration)
        }

        fun makeText(activity: Activity, message: String?, iconId: Int, duration: Int): MaterialToast {
            return makeText(activity, message, activity.resources.getDrawable(iconId), duration)
        }

        fun makeText(activity: Activity, messageId: Int, iconId: Int, duration: Int): MaterialToast {
            return makeText(activity, activity.resources.getString(messageId),
                    activity.resources.getDrawable(iconId), duration)
        }

        private fun isColorDark(color: Int): Boolean {
            return ColorUtils.calculateLuminance(color) < 0.5
        }

        private fun getRealDuration(duration: Int): Int {
            return when (duration) {
                Toast.LENGTH_SHORT -> 2000
                Toast.LENGTH_LONG -> 3500
                else -> duration
            }
        }
    }
}