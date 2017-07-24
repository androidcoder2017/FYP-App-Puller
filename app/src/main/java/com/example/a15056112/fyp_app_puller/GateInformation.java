package com.example.a15056112.fyp_app_puller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GateInformation extends AppCompatActivity {

    TextView tvTerminal, tvGateNumber, tvDetails;
    ImageView ivDirection;
    ListView lvDetails;

    DatabaseReference mDatabaseDetails;
    DatabaseReference mDatabaseDetails1;
    List<Information> detailList;

    //Asif's logic
    //ArrayList<String> alDate;
    // get current date / for loop to check if today is the date in the alDate/ if yes, .childdate(
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_information);

        tvTerminal = (TextView)findViewById(R.id.textViewTerminal);
        tvGateNumber = (TextView)findViewById(R.id.textViewGateNumber);
        ivDirection = (ImageView)findViewById(R.id.imageView);
        tvDetails = (TextView)findViewById(R.id.details);

        lvDetails = (ListView)findViewById(R.id.lvDetails);

        tvDetails.setText(Html.fromHtml("<h1><u><b>Plane Details</b></u></h1>"));

        Intent i = getIntent();

        final String terminal = i.getStringExtra("terminalname");
        final String gatename = i.getStringExtra("gatename");

        tvTerminal.setText(terminal);
        tvGateNumber.setText("Gate: " + gatename);

        detailList = new ArrayList<>();


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        final String formattedDate = df.format(calendar.getTime());

        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        final String formattedTime = tf.format(calendar.getTime());

        //mDatabaseDetails = FirebaseDatabase.getInstance().getReference().child("Gate").child(gatename).child("DaySlot").child("24-7-2017").child("Flight").child("19:00");



        mDatabaseDetails = FirebaseDatabase.getInstance().getReference().child("Gate").child(gatename).child("DaySlot").child(formattedDate).child("Flight").child(formattedTime);

        //Asif's logic
        /*mDatabaseDetails1 = FirebaseDatabase.getInstance().getReference().child("Gate").child(gatename).child("DaySlot").child(formattedDate).child("Flight");
        String date = mDatabaseDetails1.getKey().toString();
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();*/

    }


    @Override
    protected void onStart() {
        super.onStart();

        mDatabaseDetails.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot gateSnapshot) {
                detailList.clear();


                    Information info = gateSnapshot.getValue(Information.class);
                    detailList.add(info);


                final InformationAdapter adapter = new InformationAdapter(GateInformation.this, detailList);
                lvDetails.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
