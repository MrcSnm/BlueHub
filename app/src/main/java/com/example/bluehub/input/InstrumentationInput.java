package com.example.bluehub.input;

import android.app.Instrumentation;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class InstrumentationInput implements InputBinder
{
    private Instrumentation instru;
    public InstrumentationInput()
    {
        instru = new Instrumentation();
    }

    @Override
    public void sendKeyEvent(KeyEvent key)
    {
        instru.sendKeyDownUpSync(key.getKeyCode());
    }

    @Override
    public void sendMotionEvent(MotionEvent motion)
    {
        instru.sendPointerSync(motion);
    }
}
