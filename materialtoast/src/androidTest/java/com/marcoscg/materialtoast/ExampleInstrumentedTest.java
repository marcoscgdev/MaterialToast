/*
 * MaterialToastSample - ExampleInstrumentedTest.java
 * Created by Marcos Calvo García on 21/12/18 18:24.
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 21/12/18 18:24.
 */

package com.marcoscg.materialtoast;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.marcoscg.materialtoast.test", appContext.getPackageName());
    }
}
