package com.example.a15056112.fyp_app_puller;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DirectionDetailsActivity extends AppCompatActivity {
    ImageView ivDirection;
    TextView tvDirectionTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_details);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.RED));

        tvDirectionTitle = (TextView)findViewById(R.id.directionTitle);
        ivDirection = (ImageView)findViewById(R.id.ivDirection);

        Intent i = getIntent();
        final String direction = i.getStringExtra("direction");

        if(direction.equalsIgnoreCase("right") || direction.equalsIgnoreCase("not updated")) {
            ivDirection.setImageResource(R.drawable.rightdirection);
        } else {
            ivDirection.setImageResource(R.drawable.leftdirection);
        }
    }
}
