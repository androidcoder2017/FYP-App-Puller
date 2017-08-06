package com.example.a15056112.fyp_app_puller;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

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

        tvDirectionTitle.setText(Html.fromHtml("<h1><u><b>Direction</b></u></h1>"));

        Intent i = getIntent();
        String direction = i.getStringExtra("direction");

        if(direction.equalsIgnoreCase("right") || direction.equalsIgnoreCase("not updated")) {
            ivDirection.setImageResource(R.drawable.rightdirection);
        } else {
            ivDirection.setImageResource(R.drawable.leftdirection);
        }
    }
}
