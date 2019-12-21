package com.example.bluehub;

import android.content.Context;
import android.widget.Toast;

public class Util
{
    public static void printMessage(Context context, String message)
    {
        System.out.println("_____________" + message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
