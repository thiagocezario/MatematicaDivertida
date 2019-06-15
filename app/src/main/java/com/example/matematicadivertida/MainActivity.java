package com.example.matematicadivertida;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

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
                intent.putExtra("score", 0);
                intent.putExtra("questionNumber", 0);

                ArrayList<Integer> images = new ArrayList<>();
                images.add(R.drawable.image1);
                images.add(R.drawable.image2);
                images.add(R.drawable.image3);
                images.add(R.drawable.image4);
                images.add(R.drawable.image5);
                images.add(R.drawable.image6);
                images.add(R.drawable.image7);
                images.add(R.drawable.image8);
                images.add(R.drawable.image9);
                images.add(R.drawable.image10);

                intent.putIntegerArrayListExtra("images", images);
                startActivity(intent);
                finish();
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
                finish();
            }
        });

        higherNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this, HigherNumber.class
                );
                intent.putExtra("score", 0);
                intent.putExtra("questionNumber", 0);

                startActivity(intent);
                finish();
            }
        });
    }
}
