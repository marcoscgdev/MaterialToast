package com.marcoscg.materialtoastsample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.marcoscg.materialtoast.MaterialToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toastDef(View v) {
        Toast.makeText(this, "Hello, I'm a default toast!", Toast.LENGTH_SHORT).show();
    }

    public void toastMat(View v) {
        MaterialToast.makeText(this, "Hello, I'm a material toast!", R.mipmap.ic_launcher, Toast.LENGTH_SHORT).show();
    }

    public void toastMatCol(View v) {
//        MaterialToast.makeText(this, "Hello, I'm a material toast!",
//                R.mipmap.ic_launcher, Toast.LENGTH_SHORT).setBackgroundColor(Color.RED).show();

        new MaterialToast(this)
                .setMessage("Hello, I'm a material toast!")
                .setIcon(R.mipmap.ic_launcher)
                .setDuration(Toast.LENGTH_SHORT)
                .setBackgroundColor(Color.RED)
                .show();
    }
}
