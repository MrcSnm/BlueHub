package com.example.bluehub;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.ListAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

import static android.bluetooth.BluetoothAdapter.getDefaultAdapter;

public class BluetoothHandler
{
    public static final int REQUEST_ENABLE_BT = 0;
    public static final int REQUEST_DISCOVER_BT = 1;
    public static boolean WAS_BLUETOOTH_ACTIVATED = true;
    public static BluetoothAdapter adapter;
    private AppCompatActivity ctx;
    private BroadcastReceiver receiver = null;
    private boolean isRegistered = false;


    public BluetoothHandler(AppCompatActivity mainHandler)
    {
        adapter = getDefaultAdapter();
        ctx = mainHandler;
        if (adapter == null)
            Util.printMessage(ctx, "Your device does not support Bluetooth");
        if (!adapter.isEnabled())
        {
            WAS_BLUETOOTH_ACTIVATED = false;
            Intent enableBlueIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            mainHandler.startActivityForResult(enableBlueIntent, REQUEST_ENABLE_BT);
        }
        else
            Util.printMessage(ctx, "Bluetooth is already running");
    }

    public void registerReceiver()
    {
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        receiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                String action = intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action))
                {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    Util.printMessage(ctx, device.getName() + "_ADDR: " + device.getAddress());
                }
            }
        };
        ctx.registerReceiver(receiver, filter);
        if(adapter.isDiscovering())
            adapter.cancelDiscovery();
        boolean couldStart = adapter.startDiscovery();
        if(!couldStart)
            Util.printMessage(ctx, "BlueHub could not start discovering devices");
        isRegistered = true;
    }

    public void handleDisconnect()
    {
        if (!WAS_BLUETOOTH_ACTIVATED && adapter != null)
            adapter.disable();
        if(adapter.isDiscovering())
            adapter.cancelDiscovery();
        if(isRegistered)
            ctx.unregisterReceiver(receiver);
    }

    public void discoverDevice()
    {
        if (!adapter.isDiscovering())
        {
            Util.printMessage(ctx, "Your device is being discoverable");
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            ctx.startActivityForResult(intent, REQUEST_DISCOVER_BT);
        }
    }

    public void findDevices()
    {

    }

    public void showPairedDevices()
    {
        if (adapter.isEnabled())
        {
            Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
            if(pairedDevices.size() > 0)
                for(BluetoothDevice device : pairedDevices)
                    System.out.println("Name: " + device.getName() + " - MACADDR: " + device.getAddress());
        }
    }
}
