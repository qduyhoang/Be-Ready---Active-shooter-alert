package com.example.hello.shooteralert;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Instruction_1 extends AppCompatActivity {
    private Button option1;
    private Button option2;
    private TextView instruction_text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.instruction1);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        instruction_text = findViewById(R.id.instruction_text);
        final Intent next_instruction = new Intent(this, Instruction_2.class);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next_instruction.putExtra("option", "1");
                startActivity(next_instruction);
            }
        });


        option2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                next_instruction.putExtra("option", "2");
                startActivity(next_instruction);
            }
        });
    }
}
