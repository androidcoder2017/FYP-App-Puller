package com.example.a15056112.fyp_app_puller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GateInformation extends AppCompatActivity {

    TextView tvTerminal, tvGateNumber;
    ImageView ivDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_information);

        tvTerminal = (TextView)findViewById(R.id.textViewTerminal);
        tvGateNumber = (TextView)findViewById(R.id.textViewGateNumber);
        ivDirection = (ImageView)findViewById(R.id.imageView);

        Intent i = getIntent();

        String terminal = i.getStringExtra("terminalname");
        String gatename = i.getStringExtra("gatename");

        tvTerminal.setText(terminal);
        tvGateNumber.setText("Gate: " + gatename);

    }
}
