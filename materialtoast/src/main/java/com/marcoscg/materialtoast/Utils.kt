/*
 * MaterialToastSample - Utils.java
 * Created by Marcos Calvo García on 6/12/20 20:58.
 * Copyright (c) 2020. All rights reserved.
 *
 * Last modified 6/12/20 20:58.
 */
package com.marcoscg.materialtoast

import android.app.Activity
import android.graphics.Point

internal class Utils {

    companion object {

        fun getWindowMetricsSize(activity: Activity?): Point {
            val windowSize = Point()
            val windowMetricsSize = activity?.windowManager?.defaultDisplay
            windowMetricsSize?.getSize(windowSize)
            return windowSize
        }

    }

}