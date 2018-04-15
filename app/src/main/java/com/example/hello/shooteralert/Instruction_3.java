package com.example.hello.shooteralert;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class Instruction_3 extends AppCompatActivity {
    private TextView instruction_text;
    private Button back_button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.instruction3);
        Intent intent = getIntent();
        String option_id = intent.getStringExtra("option");
        switch (option_id){
            case "1":
                instruction_text.setText("1");
                break;
            case "2":
                instruction_text.setText("2");
                break;
            case "3":
                instruction_text.setText("3");
                break;
            case "4":
                instruction_text.setText("4");
                break;
        }
    }
}
