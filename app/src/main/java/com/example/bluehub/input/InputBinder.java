package com.example.bluehub.input;

import android.view.KeyEvent;
import android.view.MotionEvent;

public interface InputBinder
{
    void sendKeyEvent(KeyEvent key);
    void sendMotionEvent(MotionEvent motion);
}
