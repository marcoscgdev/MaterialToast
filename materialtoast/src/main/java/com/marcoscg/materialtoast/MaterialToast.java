/*
 * MaterialToastSample - MaterialToast.java
 * Created by Marcos Calvo García on 21/12/18 18:26.
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 21/12/18 18:26.
 */

package com.marcoscg.materialtoast;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.ColorUtils;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MaterialToast {

    private Activity activity;
    private String message;
    private Drawable icon;
    private int duration = Toast.LENGTH_SHORT;

    private static int bgColor = Color.WHITE;

    public MaterialToast(Activity activity) {
        this.activity = activity;
    }

    public MaterialToast setMessage(String message) {
        this.message = message;
        return this;
    }

    public MaterialToast setMessage(int messageRes) {
        setMessage(activity.getResources().getString(messageRes));
        return this;
    }

    public MaterialToast setIcon(Drawable icon) {
        this.icon = icon;
        return this;
    }

    public MaterialToast setIcon(int iconRes) {
        setIcon(activity.getResources().getDrawable(iconRes));
        return this;
    }

    public MaterialToast setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public MaterialToast setBackgroundColor(int bgColor) {
        MaterialToast.bgColor = bgColor;

        return this;
    }

    public void show() {
        final PopupWindow toast = getToast(activity, icon);

        if (toast != null && activity.getWindow() != null) {
            toast.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 100);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (toast != null && activity != null && !activity.isFinishing())
                    toast.dismiss();
            }
        }, getRealDuration(duration));
    }

    public static MaterialToast makeText(Activity activity, String message, Drawable icon, int duration) {
        return new MaterialToast(activity)
                .setMessage(message)
                .setIcon(icon)
                .setBackgroundColor(Color.WHITE)
                .setDuration(duration);
    }

    public static MaterialToast makeText(Activity activity, String message, int duration) {
        return makeText(activity, message, null, duration);
    }

    public static MaterialToast makeText(Activity activity, int messageId, int duration) {
        return makeText(activity, activity.getResources().getString(messageId), null, duration);
    }

    public static MaterialToast makeText(Activity activity, int messageId, Drawable icon, int duration) {
        return makeText(activity, activity.getResources().getString(messageId), icon, duration);
    }

    public static MaterialToast makeText(Activity activity, String message, int iconId, int duration) {
        return makeText(activity, message, activity.getResources().getDrawable(iconId), duration);
    }

    public static MaterialToast makeText(Activity activity, int messageId, int iconId, int duration) {
        return makeText(activity, activity.getResources().getString(messageId),
                activity.getResources().getDrawable(iconId), duration);
    }

    private PopupWindow getToast(Activity activity, Drawable icon) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null)
            return null;

        View view = inflater.inflate(R.layout.mt_main_layout, null);
        CardView cardView = view.findViewById(R.id.card_view);
        cardView.setCardBackgroundColor(bgColor);
        AppCompatTextView textView = view.findViewById(android.R.id.message);
        textView.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
        if (message != null)
            textView.setText(message);

        if (isColorDark(bgColor))
            textView.setTextColor(Color.WHITE);

        PopupWindow popupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setAnimationStyle(R.style.MaterialToastAnimation);

        if (icon != null) {
            AppCompatImageView iconIV = view.findViewById(R.id.icon);
            iconIV.setVisibility(View.VISIBLE);
            iconIV.setImageDrawable(icon);
        }

        return popupWindow;
    }

    private static boolean isColorDark(int color) {
        return ColorUtils.calculateLuminance(color) < 0.5;
    }

    private static int getRealDuration(int duration) {
        switch (duration) {
            case Toast.LENGTH_SHORT:
                return 2000;
            case Toast.LENGTH_LONG:
                return 3500;
            default:
                return duration;
        }
    }
}
