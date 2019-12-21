package com.example.bluehub;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.bluehub.BluetoothHandler.REQUEST_ENABLE_BT;


public class BlueHub extends AppCompatActivity
{

    BluetoothHandler handler;
    public static int selected;

    public static int itemSelector(Context ctx, String title, String message, String[] items)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle(title + ":\n" + message);
        builder.setItems(items, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                selected = which;
            }
        });
        builder.create().show();
        return selected;
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        handler = new BluetoothHandler(this);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        itemSelector(this, "Bluetooth Pairing", "Choose a compatible device with BlueHub", new String[]{"oi", "teste"});
    }

    @Override
    protected void onDestroy()
    {
        handler.handleDisconnect();
        super.onDestroy();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case REQUEST_ENABLE_BT:
                if (resultCode == RESULT_OK)
                {
                    Util.printMessage(this, "Blutetooth is running");
                    handler.registerReceiver();
                }
                else //User denied to turn bluetooth online
                {
                    Util.printMessage(this, "BlueHub requires bluetooth for peripherals communication");
                }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}