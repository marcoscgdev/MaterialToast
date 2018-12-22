/*
 * MaterialToastSample - MaterialToast.java
 * Created by Marcos Calvo García on 21/12/18 18:26.
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 21/12/18 18:26.
 */

package com.marcoscg.materialtoast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MaterialToast {

    private Context context;
    private String message;
    private Drawable icon;
    private int duration = Toast.LENGTH_SHORT;

    private static int bgColor = Color.WHITE;

    public MaterialToast(Context context) {
        this.context = context;
    }

    public MaterialToast setMessage(String message) {
        this.message = message;
        return this;
    }

    public MaterialToast setMessage(int messageRes) {
        setMessage(context.getResources().getString(messageRes));
        return this;
    }

    public MaterialToast setIcon(Drawable icon) {
        this.icon = icon;
        return this;
    }

    public MaterialToast setIcon(int iconRes) {
        setIcon(context.getResources().getDrawable(iconRes));
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
        Toast toast = getToast(context, icon);
        toast.show();
    }

    public static MaterialToast makeText(Context context, String message, Drawable icon, int duration) {
        return new MaterialToast(context)
                .setMessage(message)
                .setIcon(icon)
                .setBackgroundColor(Color.WHITE)
                .setDuration(duration);
    }

    public static MaterialToast makeText(Context context, String message, int duration) {
        return makeText(context, message, null, duration);
    }

    public static MaterialToast makeText(Context context, int messageId, int duration) {
        return makeText(context, context.getResources().getString(messageId), null, duration);
    }

    public static MaterialToast makeText(Context context, int messageId, Drawable icon, int duration) {
        return makeText(context, context.getResources().getString(messageId), icon, duration);
    }

    public static MaterialToast makeText(Context context, String message, int iconId, int duration) {
        return makeText(context, message, context.getResources().getDrawable(iconId), duration);
    }

    public static MaterialToast makeText(Context context, int messageId, int iconId, int duration) {
        return makeText(context, context.getResources().getString(messageId),
                context.getResources().getDrawable(iconId), duration);
    }

    private Toast getToast(Context context, Drawable icon) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null)
            return null;

        Toast toast = new Toast(context);

        View view = inflater.inflate(R.layout.mt_main_layout, null);
        CardView cardView = view.findViewById(R.id.card_view);
        cardView.setCardBackgroundColor(bgColor);
        AppCompatTextView textView = view.findViewById(android.R.id.message);
        textView.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
        if (message != null)
            textView.setText(message);

        if (isColorDark(bgColor))
            textView.setTextColor(Color.WHITE);

        toast.setView(view);

        if (icon != null) {
            AppCompatImageView iconIV = view.findViewById(R.id.icon);
            iconIV.setVisibility(View.VISIBLE);
            iconIV.setImageDrawable(icon);
        }

        toast.setDuration(duration);

        return toast;
    }

    private static boolean isColorDark(int color) {
        return ColorUtils.calculateLuminance(color) < 0.5;
    }
}
