package com.example.matematicadivertida;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    private TextView score;
    private Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int totalScore = intent.getIntExtra("score", 0);
        totalScore = totalScore*20;

        score = findViewById(R.id.score);
        goBack = findViewById(R.id.goBack);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        score.setText(String.format("%d", totalScore));
    }
}
