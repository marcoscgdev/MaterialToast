package com.marcoscg.materialtoastsample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.marcoscg.materialtoast.MaterialToast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastDef(v: View?) {
        Toast.makeText(this, "Hello, I'm a default toast!", Toast.LENGTH_SHORT).show()
    }

    fun toastMat(v: View?) {
        MaterialToast.makeText(this, "Hello, I'm a material toast!", R.mipmap.ic_launcher, Toast.LENGTH_SHORT).show()
    }

    fun toastMatCol(v: View?) {
//        MaterialToast.makeText(this, "Hello, I'm a material toast!",
//                R.mipmap.ic_launcher, Toast.LENGTH_SHORT).setBackgroundColor(Color.RED).show();
        MaterialToast(this)
                .setMessage("Hello, I'm a material toast!")
                .setIcon(R.mipmap.ic_launcher)
                .setDuration(Toast.LENGTH_SHORT)
                .setBackgroundColor(Color.RED)
                .show(v)
    }
}