package com.example.a15056112.fyp_app_puller;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.SectionIndexer;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Filter;

/**
 * Created by 15056112 on 20/6/2017.
 */

public class GateAdapter extends ArrayAdapter<Gate> {

    private Activity context;
    private List<Gate> gateList;


    public GateAdapter(Activity context, List<Gate> gateList) {
        super(context, R.layout.gatelist_layout, gateList);
        this.context = context;
        this.gateList = gateList;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewGate = inflater.inflate(R.layout.gatelist_layout, null, true);

        TextView textViewTerminal = (TextView)listViewGate.findViewById(R.id.textViewTerminalName);
        TextView textViewGate = (TextView)listViewGate.findViewById(R.id.textViewGateName);

        Gate gate = gateList.get(position);

        textViewTerminal.setText(gate.getTerminalName());
        textViewGate.setText(gate.getGateName());

        listViewGate.setBackgroundColor(Color.WHITE);

        return listViewGate;
    }

}
