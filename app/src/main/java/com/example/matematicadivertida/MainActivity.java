package com.example.matematicadivertida;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button countingButton;
    private Button basicEquationsButton;
    private Button higherNumberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countingButton = findViewById(R.id.countingButton);
        basicEquationsButton = findViewById(R.id.basicEquationsButton);
        higherNumberButton = findViewById(R.id.higherNumberButton);

        countingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this, Counting.class
                );

                startActivity(intent);
            }
        });

        basicEquationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this, Equations.class
                );
                intent.putExtra("score", 0);
                intent.putExtra("questionNumber", 0);
                startActivity(intent);
            }
        });

        higherNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this, HigherNumber.class
                );

                startActivity(intent);
            }
        });
    }
}
