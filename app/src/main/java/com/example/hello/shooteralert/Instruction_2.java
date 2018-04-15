package com.example.hello.shooteralert;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Instruction_2 extends AppCompatActivity {
    private Button option3;
    private Button option4;
    private TextView instruction_text;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        option3 = findViewById(R.id.option2);
        option4 = findViewById(R.id.option2);
        final Intent intent = getIntent();
        String option_id = intent.getStringExtra("option");
        if (option_id == "1"){
            instruction_text.setText(R.string.hide);
            option3.setText("tron dc roi/canh sat den roi'");
            option3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("option", "1");
                    startActivity(intent);
                }
            });
            option4.setText("o lai");
            option4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("option", "2");
                    startActivity(intent);
                }
            });

        }
        else if (option_id == "2"){
            instruction_text.setText(R.string.evacuate);
            option3.setText("di tan 1");
            option3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("option", "3");
                    startActivity(intent);

                }
            });
            option4.setText("di tan 2");
            option4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("option", "4");
                    startActivity(intent);

                }
            });

        }

    }
}
