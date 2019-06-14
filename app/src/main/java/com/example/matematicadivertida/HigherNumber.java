package com.example.matematicadivertida;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class HigherNumber extends AppCompatActivity {

    private TextView number1;
    private TextView number2;
    private TextView number3;

    private EditText answerField;
    private Button button;

    private int score;
    private int questionsAsked;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_number);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        questionsAsked = intent.getIntExtra("questionNumber", 0);

        number1 = findViewById(R.id.number1);
        number1.setText(String.format("%d", new Random().nextInt(10)));

        number2 = findViewById(R.id.number2);
        number2.setText(String.format("%d", new Random().nextInt(10)));

        number3 = findViewById(R.id.number3);
        number3.setText(String.format("%d", new Random().nextInt(10)));

        answerField = findViewById(R.id.highestNumber);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(HigherNumber.this);

                if (isAnswerCorrect()) {
                    b.setMessage("Parabéns, você acertou!");
                    score += 1;
                } else {
                    b.setMessage("Você errou! A resposta certa era: " + getAnswer());
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
                            Intent intent = new Intent(HigherNumber.this, HigherNumber.class);
                            intent.putExtra("score", score);
                            intent.putExtra("questionNumber", questionsAsked + 1);
                            startActivity(intent);

                        } else {
                            Intent intent = new Intent(HigherNumber.this, Result.class);
                            intent.putExtra("score", score);
                            intent.putExtra("questionNumber", questionsAsked + 1);
                            startActivity(intent);
                        }
                    }
                });

                alertDialog.show();
            }
        });
    }

    private Boolean isAnswerCorrect() {
        int correctAnswer = getAnswer();
        int answer = 0;
        try {
            answer = Integer.parseInt(answerField.getText().toString());
        } catch (Exception e) {
            AlertDialog.Builder b = new AlertDialog.Builder(HigherNumber.this);
            b.setMessage("Valor inválido");

            b.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });

            alertDialog = b.create();
            alertDialog.show();
        }


        return correctAnswer == answer;
    }

    private int getAnswer() {
        int first = Integer.parseInt(number1.getText().toString());
        int second = Integer.parseInt(number2.getText().toString());
        int third = Integer.parseInt(number3.getText().toString());

        if (first >= second) {
            if (second >= third) {
                return (first * 100) + (second * 10) + third;

            } else if (third >= first) {
                return (third * 100) + (first * 10) + second;

            } else if (first >= third) {
                return (first * 100) + (third * 10) + second;
            }
        } else {
            if (third >= second) {
                return (third * 100) + (second * 10) + first;

            } else if (third >= first) {
                return (second * 100) + (third * 10) + first;

            } else if (first >= third) {
                return (second * 100) + (first * 10) + third;

            }
        }

        return 0;
    }
}
