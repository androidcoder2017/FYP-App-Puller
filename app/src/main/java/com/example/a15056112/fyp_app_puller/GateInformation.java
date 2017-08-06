package com.example.a15056112.fyp_app_puller;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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
    ListView lvDetails;

    DatabaseReference mDatabaseDetails;
    List<Information> detailList;

    InformationAdapter adapter;

    private Query mQueryDate, mQueryTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_information);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.RED));

        tvTerminal = (TextView)findViewById(R.id.textViewTerminal);
        tvGateNumber = (TextView)findViewById(R.id.textViewGateNumber);
        tvDetails = (TextView)findViewById(R.id.details);

        lvDetails = (ListView)findViewById(R.id.lvDetails);

        tvDetails.setText(Html.fromHtml("<h1><u><b>Plane Details</b></u></h1>"));

        Intent i = getIntent();

        final String terminal = i.getStringExtra("terminalname");
        final String gatename = i.getStringExtra("gatename");

        tvTerminal.setText("Terminal: " + terminal);
        tvGateNumber.setText("Gate: " + gatename);

        detailList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-M-yyyy");
        final String formattedDate = df.format(calendar.getTime());

        /*SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        final String formattedTime = tf.format(calendar.getTime()); */


        //mDatabaseDetails = FirebaseDatabase.getInstance().getReference().child("Gate").child(gatename).child("DaySlot").child("24-7-2017").child("Flight").child("19:00");


        mDatabaseDetails = FirebaseDatabase.getInstance().getReference().child("Gate").child(gatename).child("DaySlot").child(formattedDate).child("Flight");


    }


    @Override
    protected void onStart() {
        super.onStart();

        mDatabaseDetails.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                detailList.clear();

                for (DataSnapshot gateSnapshot: dataSnapshot.getChildren()) {

                    Information info = gateSnapshot.getValue(Information.class);
                    detailList.add(info);
                }

                final InformationAdapter adapter = new InformationAdapter(GateInformation.this, detailList);
                lvDetails.setAdapter(adapter);

                lvDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Information info = detailList.get(position);
                        Intent intent = new Intent(GateInformation.this, DirectionDetailsActivity.class);
                        intent.putExtra("direction", info.getDirection());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
