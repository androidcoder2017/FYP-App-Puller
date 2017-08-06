package com.example.a15056112.fyp_app_puller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 15056112 on 12/7/2017.
 */

public class InformationAdapter extends ArrayAdapter<Information> {

    private Activity context;
    private List<Information> detailList;

    public InformationAdapter(Activity context, List<Information> detailList) {
        super(context, R.layout.gatedetails_layout, detailList);
        this.context = context;
        this.detailList = detailList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewDetails = inflater.inflate(R.layout.gatedetails_layout, null, true);

        TextView textViewDate = (TextView)listViewDetails.findViewById(R.id.textViewDate);
        TextView textViewDirection = (TextView)listViewDetails.findViewById(R.id.textViewDirection);
        TextView textViewFlightNo = (TextView)listViewDetails.findViewById(R.id.textViewFlightNo);
        TextView textViewGateID = (TextView)listViewDetails.findViewById(R.id.textViewGateID);
        TextView textViewPlaneID = (TextView)listViewDetails.findViewById(R.id.textViewPlaneID);
        TextView textViewTime = (TextView)listViewDetails.findViewById(R.id.textViewTime);

        Information info = detailList.get(position);

        textViewDate.setText(info.getDate());
        textViewDirection.setText(info.getDirection());
        textViewFlightNo.setText(info.getFlightNo());
        textViewGateID.setText(info.getGateID());
        textViewPlaneID.setText(info.getPlaneID());
        textViewTime.setText(info.getTime());


        listViewDetails.setBackgroundColor(Color.RED);


        return listViewDetails;
    }
}
