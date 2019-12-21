package com.example.bluehub;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DynamicListView extends AlertDialog.Builder
{
    List<String> items= new ArrayList<String>();
    public DynamicListView(Context context)
    {
        super(context);
    }



}
