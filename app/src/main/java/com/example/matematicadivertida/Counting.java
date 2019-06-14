package com.example.matematicadivertida;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class Counting extends AppCompatActivity {

    private ImageView imageView;
    private Button option1;
    private Button option2;
    private Button option3;

    private int score;
    private int questionsAsked;
    private AlertDialog alertDialog;
    private String correctAnswer;

    private ArrayList<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        questionsAsked = intent.getIntExtra("questionNumber", 0);
        images = intent.getIntegerArrayListExtra("images");

        imageView = findViewById(R.id.imageView);
        int imageIndex = new Random().nextInt(images.size());
        imageView.setImageResource(images.get(imageIndex));
        correctAnswer = String.valueOf(imageIndex + 1);

        option1 = findViewById(R.id.option1);
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer(option1.getText().toString());
            }
        });

        option2 = findViewById(R.id.option2);
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer(option2.getText().toString());
            }
        });

        option3 = findViewById(R.id.option3);
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer(option3.getText().toString());
            }
        });

        setupAnswers();
    }

    private void setupAnswers() {
        int index = new Random().nextInt(3);
        int answer2;
        int answer3;

        do {
            answer2 = new Random().nextInt(10) + 1;
        } while (answer2 == Integer.parseInt(correctAnswer));

        do {
            answer3 = new Random().nextInt(10) + 1;
        } while (answer3 == Integer.parseInt(correctAnswer) || answer3 == answer2);

        switch (index) {
            case 0:
                option1.setText(correctAnswer);

                option2.setText(String.valueOf(answer2));
                option3.setText(String.valueOf(answer3));
                break;
            case 1:
                option2.setText(correctAnswer);

                option1.setText(String.valueOf(answer2));
                option3.setText(String.valueOf(answer3));
                break;
            case 2:
                option3.setText(correctAnswer);

                option1.setText(String.valueOf(answer2));
                option2.setText(String.valueOf(answer3));
                break;
        }
    }

    private void validateAnswer(String option) {
        AlertDialog.Builder b = new AlertDialog.Builder(Counting.this);

        if (option == correctAnswer) {
            b.setMessage("Parabéns, você acertou!");
            score += 1;
        } else {
            b.setMessage("Você errou! A resposta certa era: " + correctAnswer);
        }

        b.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertDialog = b.create();
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (questionsAsked < 4 ) {
                    Intent intent = new Intent(Counting.this, Counting.class);
                    intent.putExtra("score", score);
                    intent.putExtra("questionNumber", questionsAsked + 1);
                    intent.putIntegerArrayListExtra("images", images);
                    startActivity(intent);
                    finish();

                } else {
                    Intent intent = new Intent(Counting.this, Result.class);
                    intent.putExtra("score", score);
                    intent.putExtra("questionNumber", questionsAsked + 1);
                    intent.putIntegerArrayListExtra("images", images);
                    startActivity(intent);
                    finish();finish();
                }
            }
        });

        alertDialog.show();
    }
}
